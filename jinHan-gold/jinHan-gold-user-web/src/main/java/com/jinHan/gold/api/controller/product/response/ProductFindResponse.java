package com.jinHan.gold.api.controller.product.response;

import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.variety.domain.model.Variety;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: ProductFindResponse
 * 描述: 宠物商品查询响应
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class ProductFindResponse {
    /**
     * 商品唯一标识
     */
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
    private String birthday;

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

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updatedTime;


    /*
    *  品种信息
    * */

    private Variety variety;

    public ProductFindResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.varietyType = product.getVarietyType();
        this.originPrice = product.getOriginPrice();
        this.price = product.getPrice();
        this.activityCategory = product.getActivityCategory();
        this.status = product.getStatus();
        this.isExcellence = product.getIsExcellence();
        this.isShipFree = product.getIsShipFree();
        this.title = product.getTitle();
        this.gender = product.getGender();
        this.birthday = product.getBirthday() != null ? product.getBirthday().toString() : null;
        this.description = product.getDescription();
        this.mainImage = product.getMainImage();
        this.images = product.getImages();
        this.createdTime = product.getCreatedTime() != null ? product.getCreatedTime().toString() : null;
        this.updatedTime = product.getUpdatedTime() != null ? product.getUpdatedTime().toString() : null;
    }
}
