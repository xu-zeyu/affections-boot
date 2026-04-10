package com.jinHan.gold.core.shipping.domain.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 类名: ShippingCreateCommand
 * 描述: 创建发货记录命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
public class ShippingCreateCommand {
    
    @NotBlank(message = "订单ID不能为空")
    private String orderId;

    private String logisticsCompany;

    private String logisticsNo;

    @NotBlank(message = "发货地址不能为空")
    private String shippingAddress;

    private String remark;
}