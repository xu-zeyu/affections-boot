package com.jinHan.gold.core.shipping.domain.command;

import com.jinHan.gold.core.shipping.domain.model.ShippingStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类名: ShippingStatusUpdateCommand
 * 描述: 更新发货状态命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
public class ShippingStatusUpdateCommand {
    
    @NotNull(message = "发货记录ID不能为空")
    private Long id;
    
    @NotNull(message = "发货状态不能为空")
    private ShippingStatusEnum status;
}