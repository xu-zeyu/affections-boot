package com.jinHan.gold.core.order.domain.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 类名: OrderShipCommand
 * 描述: 订单发货命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
public class OrderShipCommand {
    
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
    
    private String logisticsCompany;
    
    private String logisticsNo;
    
    private String shippingAddress;

    private String remark;
}
