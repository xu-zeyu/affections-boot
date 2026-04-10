package com.jinHan.gold.api.controller.product.request;

import com.jinHan.gold.core.product.domain.command.ProductUpdateCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: ProductUpdateRequest
 * 描述: 更新商品请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class ProductUpdateRequest {
    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Long id;

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
    private java.math.BigDecimal originPrice;

    /**
     * 当前售价
     */
    private java.math.BigDecimal price;

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


    public ProductUpdateCommand toCommand() {
        ProductUpdateCommand command = new ProductUpdateCommand();
        command.setId(this.id);
        command.setName(this.name);
        command.setVarietyType(this.varietyType);
        command.setOriginPrice(this.originPrice);
        command.setPrice(this.price);
        command.setActivityCategory(this.activityCategory);
        command.setStatus(this.status);
        command.setIsExcellence(this.isExcellence);
        command.setIsShipFree(this.isShipFree);
        command.setTitle(this.title);
        command.setGender(this.gender);
        command.setBirthday(this.birthday);
        command.setDescription(this.description);
        command.setMainImage(this.mainImage);
        command.setImages(this.images);
        return command;
    }

}
