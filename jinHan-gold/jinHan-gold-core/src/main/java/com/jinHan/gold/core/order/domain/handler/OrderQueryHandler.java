package com.jinHan.gold.core.order.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.order.domain.command.OrderPageQueryCommand;
import com.jinHan.gold.core.order.domain.mapper.OrderItemMapper;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderItem;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类名: OrderQueryHandler
 * 描述: 订单查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class OrderQueryHandler {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    /**
     * 分页查询订单列表
     */
    public IPage<Orders> queryPage(OrderPageQueryCommand command) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<Orders>()
                .eq(command.getUserId() != null, Orders::getUserId, command.getUserId())
                .eq(command.getStatus() != null, Orders::getStatus, command.getStatus())
                .eq(command.getOrderId() != null, Orders::getOrderId, command.getOrderId())
                .orderByDesc(Orders::getCreatedTime);

        return orderMapper.selectPage(new Page<>(command.getPage(), command.getSize()), wrapper);
    }

    /**
     * 根据订单ID查询订单详情（包含明细）
     */
    public Orders queryDetail(String orderId) {
        Orders order = orderMapper.selectById(orderId);
        if (order != null) {
            List<OrderItem> orderItems = orderItemMapper.selectList(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId)
            );
            order.setOrderItems(orderItems);
        }
        return order;
    }
}
