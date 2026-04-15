package com.jinHan.gold.core.order.domain.handler;

import com.jinHan.gold.core.order.domain.command.OrderShipCommand;
import com.jinHan.gold.core.shipping.domain.command.ShippingCreateCommand;
import com.jinHan.gold.core.shipping.domain.handler.ShippingCreateHandler;
import com.jinHan.gold.core.todo.domain.event.BizEventPublisher;
import com.jinHan.gold.core.todo.domain.event.BizEventTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: OrderShipHandler
 * 描述: 订单发货处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Component
public class OrderShipHandler {

    @Resource
    private ShippingCreateHandler shippingCreateHandler;

    @Resource
    private BizEventPublisher bizEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public Long ship(OrderShipCommand command) {
        // 创建发货订单
        ShippingCreateCommand shippingCreateCommand = new ShippingCreateCommand();
        shippingCreateCommand.setOrderId(command.getOrderId());
        shippingCreateCommand.setShippingAddress(command.getShippingAddress());
        shippingCreateCommand.setRemark(command.getRemark());
        shippingCreateCommand.setLogisticsCompany(command.getLogisticsCompany());
        shippingCreateCommand.setLogisticsNo(command.getLogisticsNo());

        Long shippingId = shippingCreateHandler.create(shippingCreateCommand);
        bizEventPublisher.publish(BizEventTypeEnum.ORDER_SHIPPED, command.getOrderId(), null);
        return shippingId;
    }
}
