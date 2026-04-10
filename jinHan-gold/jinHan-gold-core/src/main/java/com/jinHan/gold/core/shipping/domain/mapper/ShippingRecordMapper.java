package com.jinHan.gold.core.shipping.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名: ShippingRecordMapper
 * 描述: 发货记录Mapper接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Mapper
public interface ShippingRecordMapper extends BaseMapper<ShippingRecord> {
}