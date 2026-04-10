package com.jinHan.gold.core.order.domain.command;

import lombok.Data;

/**
 * 类名: OrderCancelCommand
 * 描述: 订单取消命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
public class OrderCancelCommand {
    private String orderId;
    private String cancelReason;
}
