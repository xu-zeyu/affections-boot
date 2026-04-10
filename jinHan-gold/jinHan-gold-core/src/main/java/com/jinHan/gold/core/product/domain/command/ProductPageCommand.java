package com.jinHan.gold.core.product.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: ProductPageCommand
 * 描述: 分页查询宠物商品命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPageCommand extends PageParam {
    /**
     * 宠物名称（模糊查询）
     */
    private String name;

    /**
     * 宠物品种
     */
    private Long varietyType;

    /**
     * 宠物状态：0-下架，1-上架
     */
    private Integer status;

    /*
     * 性别
     * */
    private Integer gender;
}
