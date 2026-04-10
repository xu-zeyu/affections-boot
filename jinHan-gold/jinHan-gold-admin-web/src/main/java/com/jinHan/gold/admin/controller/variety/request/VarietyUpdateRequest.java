package com.jinHan.gold.admin.controller.variety.request;

import com.jinHan.gold.core.variety.domain.command.VarietyUpdateCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: VarietyUpdateRequest
 * 描述: 更新品种请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
public class VarietyUpdateRequest {
    /**
     * 品种ID
     */
    @NotNull(message = "品种ID不能为空")
    private Long id;

    /**
     * 品种名称
     */
    private String name;

    /**
     * 分类图标URL
     */
    private String iconUrl;

    /**
     * 最低价格
     */
    private BigDecimal minPrice;

    /**
     * 排序顺序，数字越小越靠前
     */
    private Integer sortOrder;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;

    public VarietyUpdateCommand toCommand() {
        VarietyUpdateCommand command = new VarietyUpdateCommand();
        command.setId(this.id);
        command.setName(this.name);
        command.setIconUrl(this.iconUrl);
        command.setMinPrice(this.minPrice);
        command.setSortOrder(this.sortOrder);
        command.setStatus(this.status);
        return command;
    }
}
