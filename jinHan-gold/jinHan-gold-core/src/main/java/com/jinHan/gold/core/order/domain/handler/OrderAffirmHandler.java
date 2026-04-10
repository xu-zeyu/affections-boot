package com.jinHan.gold.core.order.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.order.domain.command.OrderAffirmCommand;
import com.jinHan.gold.core.order.domain.command.OrderShipCommand;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.shipping.domain.command.ShippingCreateCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: OrderAffirmHandler
 * 描述: 订单确认处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class OrderAffirmHandler {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderShipHandler orderShipHandler;

    @Transactional(rollbackFor = Exception.class)
    public void affirm(OrderAffirmCommand command) {
        Orders order = orderMapper.selectById(command.getOrderId());
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        // 检查订单状态是否为待确认
        if (order.getStatus() != OrderStatusEnum.PENDING_CONFIRM) {
            throw new BusinessException("订单状态不是待确认状态");
        }

        // 确认后状态改为待发货
        order.setStatus(OrderStatusEnum.PROCESSING);
        order.setUpdatedTime(LocalDateTime.now());

        int updated = orderMapper.updateById(order);
        if (updated <= 0) {
            throw new BusinessException("订单确认失败");
        }

        // 创建发货订单
        OrderShipCommand orderShipCommand = new OrderShipCommand();
        orderShipCommand.setOrderId(command.getOrderId());
        orderShipCommand.setShippingAddress(order.getAddress());
        Long shipped = orderShipHandler.ship(orderShipCommand);
        if (shipped <= 0) {
            throw new BusinessException("订单确认失败");
        }
    }
}
