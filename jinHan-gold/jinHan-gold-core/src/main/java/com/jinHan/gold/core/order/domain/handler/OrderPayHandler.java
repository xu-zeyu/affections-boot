package com.jinHan.gold.core.order.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordCreateCommand;
import com.jinHan.gold.core.finance.domain.handler.IncomeRecordCreateHandler;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordStatusEnum;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordTypeEnum;
import com.jinHan.gold.core.order.domain.command.OrderPayCommand;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.order.domain.model.PaymentMethodEnum;
import com.jinHan.gold.core.todo.domain.event.BizEventPublisher;
import com.jinHan.gold.core.todo.domain.event.BizEventTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: OrderPayHandler
 * 描述: 订单支付处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class OrderPayHandler {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private  IncomeRecordCreateHandler incomeRecordCreateHandler;

    @Resource
    private BizEventPublisher bizEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public void pay(OrderPayCommand command) {
        Orders order = orderMapper.selectById(command.getOrderId());
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        // 检查订单状态是否为待付款
        if (order.getStatus() != OrderStatusEnum.PENDING_PAYMENT) {
            throw new BusinessException("订单状态不是待付款状态");
        }

        // 更新订单状态为已付款（等待财务审核）
        order.setStatus(OrderStatusEnum.PAID);
        order.setPaymentMethod(command.getPaymentMethod());
        order.setPaymentAmount(order.getTotalAmount()); // 实际支付金额等于订单总金额
        order.setPaymentTime(LocalDateTime.now());
        order.setUpdatedTime(LocalDateTime.now());
        
        int updated = orderMapper.updateById(order);
        
        if (updated <= 0) {
            throw new BusinessException("订单支付失败");
        }

        // 创建收入记录（待审核状态）
        IncomeRecordCreateCommand incomeRecordCreateCommand = new IncomeRecordCreateCommand();
        incomeRecordCreateCommand.setType(IncomeRecordTypeEnum.PRODUCT_CAT_SALE);
        incomeRecordCreateCommand.setOrderId(command.getOrderId());
        incomeRecordCreateCommand.setDescribed(IncomeRecordTypeEnum.PRODUCT_CAT_SALE.getDescription());
        incomeRecordCreateCommand.setMoney(order.getTotalAmount());
        incomeRecordCreateCommand.setEvidenceUrl(command.getEvidence());
        incomeRecordCreateHandler.create(incomeRecordCreateCommand);
        bizEventPublisher.publish(BizEventTypeEnum.ORDER_PAID, order.getOrderId(), order.getUserId());
    }
}
