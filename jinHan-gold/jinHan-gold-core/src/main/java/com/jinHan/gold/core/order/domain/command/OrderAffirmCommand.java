package com.jinHan.gold.core.order.domain.command;

import lombok.Data;

/**
 * 类名: OrderAffirmCommand
 * 描述: 订单确认命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
public class OrderAffirmCommand {
    private String orderId;
    private Boolean approved;
    private String auditReason;
}
