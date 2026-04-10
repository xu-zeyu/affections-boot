package com.jinHan.gold.core.shipping.domain.model;

/**
 * 类名: ShippingStatusEnum
 * 描述: 发货状态枚举
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
public enum ShippingStatusEnum {
    PENDING_SHIP,    // 待发货
    SHIPPED,         // 已发货
    IN_TRANSIT,      // 运输中
    DELIVERED,       // 已送达
    FAILED           // 发货失败
}