package com.jinHan.gold.core.variety.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: Variety
 * 描述: 宠物品种实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "variety")
public class Variety extends BaseEntity {
    /**
     * 品种ID
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;
}
