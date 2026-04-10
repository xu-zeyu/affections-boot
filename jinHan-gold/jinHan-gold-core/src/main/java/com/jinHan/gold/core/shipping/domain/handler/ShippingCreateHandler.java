package com.jinHan.gold.core.shipping.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.shipping.domain.command.ShippingCreateCommand;
import com.jinHan.gold.core.shipping.domain.mapper.ShippingRecordMapper;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import com.jinHan.gold.core.shipping.domain.model.ShippingStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: ShippingCreateHandler
 * 描述: 创建发货记录处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Component
public class ShippingCreateHandler {

    @Resource
    private ShippingRecordMapper shippingRecordMapper;
    
    @Resource
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long create(ShippingCreateCommand command) {
        // 验证订单是否存在
        Orders order = orderMapper.selectById(command.getOrderId());
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        // 检查订单状态是否为待发货或处理中
        if (order.getStatus() != OrderStatusEnum.PROCESSING) {
            throw new BusinessException("订单状态不是处理中状态，无法发货");
        }
        
        // 检查是否已存在发货记录
        ShippingRecord existingRecord = shippingRecordMapper.selectById(command.getOrderId());
        if (existingRecord != null) {
            throw new BusinessException("该订单已创建发货记录");
        }

        // 创建发货记录
        ShippingRecord record = new ShippingRecord();
        record.setOrderId(command.getOrderId());
        record.setLogisticsCompany(command.getLogisticsCompany());
        record.setLogisticsNo(command.getLogisticsNo());
        record.setShippingAddress(command.getShippingAddress());
        
        // 根据是否有物流信息设置状态：有快递信息则为已发货，否则为待发货
        if (command.getLogisticsCompany() != null && !command.getLogisticsCompany().trim().isEmpty() &&
            command.getLogisticsNo() != null && !command.getLogisticsNo().trim().isEmpty()) {
            record.setStatus(ShippingStatusEnum.SHIPPED);
            record.setShipTime(LocalDateTime.now());
        } else {
            record.setStatus(ShippingStatusEnum.PENDING_SHIP);
        }
        
        record.setRemark(command.getRemark());
        record.setCreatedTime(LocalDateTime.now());
        record.setUpdatedTime(LocalDateTime.now());

        int inserted = shippingRecordMapper.insert(record);
        if (inserted <= 0) {
            throw new BusinessException("创建发货记录失败");
        }
        
        return record.getId();
    }
}