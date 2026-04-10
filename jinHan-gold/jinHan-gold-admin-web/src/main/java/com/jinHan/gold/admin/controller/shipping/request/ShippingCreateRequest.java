package com.jinHan.gold.admin.controller.shipping.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 类名: ShippingCreateRequest
 * 描述: 创建发货记录请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
public class ShippingCreateRequest {
    
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
    
    @NotBlank(message = "物流公司不能为空")
    private String logisticsCompany;
    
    @NotBlank(message = "物流单号不能为空")
    private String logisticsNo;
    
    @NotBlank(message = "发货地址不能为空")
    private String shippingAddress;
    
    private String remark;
}