package com.jinHan.gold.core.finance.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.finance.domain.command.ConfirmPaymentCommand;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordStatusEnum;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: ConfirmPaymentHandler
 * 描述: 确认付款处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class ConfirmPaymentHandler {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public void confirm(ConfirmPaymentCommand command) {
        // 1. 更新收入记录状态为已确认
        LambdaQueryWrapper<IncomeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IncomeRecord::getId, command.getId());
        IncomeRecord incomeRecord = incomeRecordMapper.selectOne(queryWrapper);

        if (incomeRecord == null) {
            throw new BusinessException("收入记录不存在");
        }

        // 如果收入记录已经是已确认状态，直接返回
        if (incomeRecord.getStatus() == IncomeRecordStatusEnum.CONFIRMED) {
            return;
        }

        // 更新收入记录状态为已确认
        incomeRecord.setStatus(IncomeRecordStatusEnum.CONFIRMED);
        incomeRecord.setReviewedBy(command.getReviewedBy());
        incomeRecord.setReviewedTime(LocalDateTime.now());

        int incomeUpdated = incomeRecordMapper.updateById(incomeRecord);
        if (incomeUpdated <= 0) {
            throw new BusinessException("更新收入记录状态失败");
        }


        // 2. 更新订单状态为已确认
        Orders order = orderMapper.selectById(command.getOrderId());
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        if (order.getStatus() != OrderStatusEnum.PAID) {
            throw new BusinessException("订单状态不是已付款状态");
        }

        order.setStatus(OrderStatusEnum.PENDING_CONFIRM);

        int orderUpdated = orderMapper.updateById(order);
        if (orderUpdated <= 0) {
            throw new BusinessException("更新订单状态失败");
        }
    }
}
