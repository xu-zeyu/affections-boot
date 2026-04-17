package com.jinHan.gold.core.todo.domain.factory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.admin.domain.mapper.AdminMapper;
import com.jinHan.gold.core.order.domain.mapper.OrderItemMapper;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderItem;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.todo.domain.command.TodoCompleteCommand;
import com.jinHan.gold.core.todo.domain.command.TodoCreateCommand;
import com.jinHan.gold.core.todo.domain.event.BizEvent;
import com.jinHan.gold.core.todo.domain.event.BizEventTypeEnum;
import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 根据业务事件构建待办动作
 */
@Component
public class TodoFactory {

    private static final String ORDER_PAY = "ORDER_PAY";
    private static final String ORDER_SHIP = "ORDER_SHIP";
    private static final String ORDER_SHIP_PERMISSION_CODE = "ORDER_LIST_SHIP";

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private AdminMapper adminMapper;

    @Value("${todo.order-pay-timeout-minutes:30}")
    private long orderPayTimeoutMinutes;

    @Value("${todo.order-ship-timeout-hours:24}")
    private long orderShipTimeoutHours;

    public TodoBuildResult build(BizEvent event) {
        TodoBuildResult result = new TodoBuildResult();
        Orders order = orderMapper.selectById(event.getBizId());
        if (order == null) {
            return result;
        }

        return switch (event.getType()) {
            case ORDER_CREATED -> buildOrderCreated(result, event, order);
            case ORDER_PAID -> buildOrderPaid(result, event, order);
            case ORDER_CANCELLED -> buildOrderCancelled(result, order);
            case ORDER_SHIPPED -> buildOrderShipped(result, order);
        };
    }

    private TodoBuildResult buildOrderCreated(TodoBuildResult result, BizEvent event, Orders order) {
        TodoCreateCommand command = new TodoCreateCommand();
        command.setBizType(ORDER_PAY);
        command.setBizId(order.getOrderId());
        command.setReceiverType(TodoReceiverTypeEnum.USER);
        command.setReceiverId(order.getUserId());
        command.setTitle("订单待支付");
        command.setContent(buildUserPayContent(order.getOrderId()));
        command.setSourceEvent(event.getType().name());
        command.setExpireTime(LocalDateTime.now().plusMinutes(orderPayTimeoutMinutes));
        result.getCreateCommands().add(command);
        return result;
    }

    private TodoBuildResult buildOrderPaid(TodoBuildResult result, BizEvent event, Orders order) {
        TodoCompleteCommand completeCommand = new TodoCompleteCommand();
        completeCommand.setBizType(ORDER_PAY);
        completeCommand.setBizId(order.getOrderId());
        completeCommand.setReceiverType(TodoReceiverTypeEnum.USER);
        completeCommand.setReceiverId(order.getUserId());
        result.getCompleteCommands().add(completeCommand);

        List<Long> adminIds = adminMapper.selectAdminIdsByPermissionCode(ORDER_SHIP_PERMISSION_CODE);
        for (Long adminId : adminIds) {
            TodoCreateCommand createCommand = new TodoCreateCommand();
            createCommand.setBizType(ORDER_SHIP);
            createCommand.setBizId(order.getOrderId());
            createCommand.setReceiverType(TodoReceiverTypeEnum.ADMIN);
            createCommand.setReceiverId(adminId);
            createCommand.setTitle("订单待发货");
            createCommand.setContent(buildAdminShipContent(order.getOrderId()));
            createCommand.setSourceEvent(event.getType().name());
            createCommand.setExpireTime(LocalDateTime.now().plusHours(orderShipTimeoutHours));
            result.getCreateCommands().add(createCommand);
        }
        return result;
    }

    private TodoBuildResult buildOrderCancelled(TodoBuildResult result, Orders order) {
        TodoCompleteCommand completeCommand = new TodoCompleteCommand();
        completeCommand.setBizType(ORDER_PAY);
        completeCommand.setBizId(order.getOrderId());
        completeCommand.setReceiverType(TodoReceiverTypeEnum.USER);
        completeCommand.setReceiverId(order.getUserId());
        result.getCompleteCommands().add(completeCommand);
        return result;
    }

    private TodoBuildResult buildOrderShipped(TodoBuildResult result, Orders order) {
        TodoCompleteCommand completeCommand = new TodoCompleteCommand();
        completeCommand.setBizType(ORDER_SHIP);
        completeCommand.setBizId(order.getOrderId());
        completeCommand.setReceiverType(TodoReceiverTypeEnum.ADMIN);
        result.getCompleteCommands().add(completeCommand);
        return result;
    }

    private String buildUserPayContent(String orderId) {
        OrderItem orderItem = orderItemMapper.selectOne(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId).last("limit 1")
        );
        String productName = orderItem == null ? "商品" : orderItem.getProductName();
        return "订单 " + orderId + " 已创建，请尽快完成 " + productName + " 的付款。";
    }

    private String buildAdminShipContent(String orderId) {
        OrderItem orderItem = orderItemMapper.selectOne(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId).last("limit 1")
        );
        String productName = orderItem == null ? "商品" : orderItem.getProductName();
        return "订单 " + orderId + " 已完成支付，请尽快安排 " + productName + " 发货。";
    }
}
