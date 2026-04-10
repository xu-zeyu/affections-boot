package com.jinHan.gold.admin.controller.product.request;

import com.jinHan.gold.core.product.domain.command.ProductPageCommand;
import lombok.Data;

/**
 * 类名: ProductPageRequest
 * 描述: 分页查询宠物商品请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class ProductPageRequest {
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

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

    public ProductPageCommand toCommand() {
        ProductPageCommand command = new ProductPageCommand();
        command.setPage(this.page);
        command.setSize(this.size);
        command.setName(this.name);
        command.setVarietyType(this.varietyType);
        command.setStatus(this.status);
        command.setGender(this.gender);
        return command;
    }
}
