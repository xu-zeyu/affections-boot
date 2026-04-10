package com.jinHan.gold.admin.controller.variety.response;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: VarietyResponse
 * 描述: 品种响应对象
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VarietyResponse extends BaseEntity {
    /**
     * 品种ID
     */
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

}
