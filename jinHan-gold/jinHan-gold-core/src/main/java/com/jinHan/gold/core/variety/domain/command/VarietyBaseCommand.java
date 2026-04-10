package com.jinHan.gold.core.variety.domain.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: VarietyBaseCommand
 * 描述: 宠物品种基础命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
public class VarietyBaseCommand {
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
}
