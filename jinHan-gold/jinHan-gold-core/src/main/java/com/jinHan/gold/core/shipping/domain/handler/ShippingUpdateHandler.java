package com.jinHan.gold.core.shipping.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.shipping.domain.command.ShippingUpdateCommand;
import com.jinHan.gold.core.shipping.domain.mapper.ShippingRecordMapper;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: ShippingUpdateHandler
 * 描述: 发货记录更新处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Component
public class ShippingUpdateHandler {

    @Resource
    private ShippingRecordMapper shippingRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public void update(ShippingUpdateCommand command) {
        ShippingRecord record = shippingRecordMapper.selectById(command.getId());
        if (record == null) {
            throw new BusinessException("发货记录不存在");
        }

        if (command.getLogisticsCompany() != null) {
            record.setLogisticsCompany(command.getLogisticsCompany());
        }
        
        if (command.getLogisticsNo() != null) {
            record.setLogisticsNo(command.getLogisticsNo());
        }
        
        if (command.getShippingAddress() != null) {
            record.setShippingAddress(command.getShippingAddress());
        }
        
        if (command.getRemark() != null) {
            record.setRemark(command.getRemark());
        }
        
        record.setUpdatedTime(LocalDateTime.now());
        
        int updated = shippingRecordMapper.updateById(record);
        if (updated <= 0) {
            throw new BusinessException("更新发货记录失败");
        }
    }
}