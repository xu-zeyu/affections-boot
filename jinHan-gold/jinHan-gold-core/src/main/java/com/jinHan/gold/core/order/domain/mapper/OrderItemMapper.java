package com.jinHan.gold.core.order.domain.mapper;

import com.affectionsboot.starter.mybatis.mapper.BaseMapperX;
import com.jinHan.gold.core.order.domain.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名: OrderItemMapper
 * 描述: 订单商品明细数据库操作mapper
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Mapper
public interface OrderItemMapper extends BaseMapperX<OrderItem> {
}
