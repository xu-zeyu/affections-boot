package com.jinHan.gold.core.order.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.order.domain.command.OrderCancelCommand;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: OrderCancelHandler
 * 描述: 订单取消处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class OrderCancelHandler {

    @Resource
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void cancel(OrderCancelCommand command) {
        Orders order = orderMapper.selectById(command.getOrderId());
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        // 检查订单状态是否可以取消
        if (order.getStatus() == OrderStatusEnum.COMPLETED || 
            order.getStatus() == OrderStatusEnum.CLOSED) {
            throw new BusinessException("订单已完成或已关闭，无法取消");
        }

        // 更新订单状态为已取消
        order.setStatus(OrderStatusEnum.CLOSED);
        order.setUpdatedTime(LocalDateTime.now());
        
        int updated = orderMapper.updateById(order);
        if (updated <= 0) {
            throw new BusinessException("订单取消失败");
        }
    }
}
