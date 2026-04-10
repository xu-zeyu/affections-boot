package com.jinHan.gold.admin.controller.shipping.request;

import com.jinHan.gold.core.shipping.domain.model.ShippingStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类名: ShippingStatusUpdateRequest
 * 描述: 更新发货状态请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
public class ShippingStatusUpdateRequest {
    
    @NotNull(message = "发货记录ID不能为空")
    private Long id;
    
    @NotNull(message = "发货状态不能为空")
    private ShippingStatusEnum status;
}