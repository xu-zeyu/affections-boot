package com.jinHan.gold.core.shipping.domain.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类名: ShippingUpdateCommand
 * 描述: 更新发货记录命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
public class ShippingUpdateCommand {
    
    @NotNull(message = "发货记录ID不能为空")
    private Long id;
    
    private String logisticsCompany;
    
    private String logisticsNo;
    
    private String shippingAddress;
    
    private String remark;
}