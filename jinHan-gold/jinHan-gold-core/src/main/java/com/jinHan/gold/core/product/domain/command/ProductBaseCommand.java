package com.jinHan.gold.core.product.domain.command;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 类名: ProductBaseCommand
 * 描述: 宠物商品基础命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class ProductBaseCommand {
    /**
     * 宠物名称
     */
    private String name;

    /**
     * 宠物品种
     */
    private Long varietyType;

    /**
     * 原始价格
     */
    private BigDecimal originPrice;

    /**
     * 当前售价
     */
    private BigDecimal price;

    /**
     * 活动分类
     */
    private Long activityCategory;

    /**
     * 宠物在售状态，1-上架 0-下架
     */
    private Integer status;

    /**
     * 是否优选，0否1是
     */
    private Integer isExcellence;

    /**
     * 是否包邮，0否1是
     */
    private Integer isShipFree;

    /**
     * 商品标题，如"高地长毛，波斯系..."
     */
    private String title;

    /**
     * 宠物性别，1代表雄性
     */
    private Integer gender;

    /**
     * 宠物出生日期
     */
    private LocalDate birthday;

    /**
     * 商品详细描述
     */
    private String description;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品图片列表(JSON格式存储)
     */
    private String images;
}
