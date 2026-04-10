package com.jinHan.gold.admin.controller.variety.request;

import com.jinHan.gold.core.variety.domain.command.VarietyCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: VarietyCreateRequest
 * 描述: 创建品种请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
public class VarietyCreateRequest {
    /**
     * 品种名称
     */
    @NotBlank(message = "品种名称不能为空")
    private String name;

    /**
     * 分类图标URL
     */
    private String iconUrl;

    /**
     * 最低价格
     */
    @NotNull(message = "最低价格不能为空")
    private BigDecimal minPrice;

    /**
     * 排序顺序，数字越小越靠前
     */
    private Integer sortOrder;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;

    public VarietyCreateCommand toCommand() {
        VarietyCreateCommand command = new VarietyCreateCommand();
        command.setName(this.name);
        command.setIconUrl(this.iconUrl);
        command.setMinPrice(this.minPrice);
        command.setSortOrder(this.sortOrder);
        command.setStatus(this.status);
        return command;
    }
}
