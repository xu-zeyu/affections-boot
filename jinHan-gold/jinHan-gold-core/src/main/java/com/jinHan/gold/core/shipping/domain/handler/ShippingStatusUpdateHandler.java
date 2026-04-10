package com.jinHan.gold.core.shipping.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.shipping.domain.command.ShippingStatusUpdateCommand;
import com.jinHan.gold.core.shipping.domain.mapper.ShippingRecordMapper;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import com.jinHan.gold.core.shipping.domain.model.ShippingStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: ShippingStatusUpdateHandler
 * 描述: 发货状态更新处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Component
public class ShippingStatusUpdateHandler {

    @Resource
    private ShippingRecordMapper shippingRecordMapper;
    
    @Resource
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(ShippingStatusUpdateCommand command) {
        ShippingRecord record = shippingRecordMapper.selectById(command.getId());
        if (record == null) {
            throw new BusinessException("发货记录不存在");
        }

        // 更新发货状态
        record.setStatus(command.getStatus());
        record.setUpdatedTime(LocalDateTime.now());
        
        // 如果是发货状态，设置发货时间
        if (command.getStatus() == ShippingStatusEnum.SHIPPED) {
            record.setShipTime(LocalDateTime.now());
        }
        
        int updated = shippingRecordMapper.updateById(record);
        if (updated <= 0) {
            throw new BusinessException("更新发货状态失败");
        }
        
        // 如果状态更新为已发货，同步更新订单状态
        if (command.getStatus() == ShippingStatusEnum.SHIPPED) {
            Orders order = orderMapper.selectById(record.getOrderId());
            if (order != null && order.getStatus() == OrderStatusEnum.PROCESSING) {
                order.setStatus(OrderStatusEnum.SHIPPED);
                order.setUpdatedTime(LocalDateTime.now());
                orderMapper.updateById(order);
            }
        }
    }
}