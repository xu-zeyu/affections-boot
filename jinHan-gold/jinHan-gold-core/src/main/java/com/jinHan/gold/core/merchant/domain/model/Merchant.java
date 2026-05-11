package com.jinHan.gold.core.merchant.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: Merchant
 * 描述: 商家实体类
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "merchant")
public class Merchant extends BaseEntity {
    /**
     * 商家ID，主键
     */
    @TableId(type = IdType.AUTO)
    private Long merchantId;

    /**
     * 关联用户ID
     */
    private Long userId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 营业执照号
     */
    private String businessLicense;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 商家简介
     */
    private String description;

    /**
     * 商家Logo
     */
    private String logo;

    /**
     * 商家状态
     */
    private MerchantStatusEnum status;
}
