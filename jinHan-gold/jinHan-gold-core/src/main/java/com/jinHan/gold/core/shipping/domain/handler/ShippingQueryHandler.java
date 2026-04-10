package com.jinHan.gold.core.shipping.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.shipping.domain.command.ShippingPageQueryCommand;
import com.jinHan.gold.core.shipping.domain.mapper.ShippingRecordMapper;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import com.jinHan.gold.core.shipping.domain.model.ShippingStatusEnum;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 类名: ShippingQueryHandler
 * 描述: 发货记录查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Component
public class ShippingQueryHandler {

    @Resource
    private ShippingRecordMapper shippingRecordMapper;

    public IPage<ShippingRecord> queryPage(ShippingPageQueryCommand command) {
        Page<ShippingRecord> page = new Page<>(command.getPage(), command.getSize());
        
        LambdaQueryWrapper<ShippingRecord> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.isNotBlank(command.getOrderId())) {
            wrapper.eq(ShippingRecord::getOrderId, command.getOrderId());
        }
        
        if (StringUtils.isNotBlank(command.getLogisticsNo())) {
            wrapper.eq(ShippingRecord::getLogisticsNo, command.getLogisticsNo());
        }
        
        if (StringUtils.isNotBlank(command.getStatus())) {
            try {
                ShippingStatusEnum statusEnum = ShippingStatusEnum.valueOf(command.getStatus());
                wrapper.eq(ShippingRecord::getStatus, statusEnum);
            } catch (IllegalArgumentException e) {
                throw new BusinessException("无效的发货状态");
            }
        }
        
        wrapper.orderByDesc(ShippingRecord::getCreatedTime);
        
        return shippingRecordMapper.selectPage(page, wrapper);
    }

    public ShippingRecord queryDetail(Long id) {
        ShippingRecord record = shippingRecordMapper.selectById(id);
        if (record == null) {
            throw new BusinessException("发货记录不存在");
        }
        return record;
    }
}