package com.jinHan.gold.core.order.domain.model;

public enum OrderStatusEnum {
    PENDING_PAYMENT,    // 待付款
    PAID,               // 已付款 -> 等待财务审核
    PENDING_CONFIRM,    // 财务审核 -> 待确认
    PROCESSING,         // 处理中
    SHIPPED,            // 已发货
    COMPLETED,          // 已完成
    CLOSED              // 已取消
}
