package com.jinHan.gold.core.merchant.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 类名: MerchantApplication
 * 描述: 商家申请实体类
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "merchant_application")
public class MerchantApplication extends BaseEntity {
    /**
     * 申请ID，主键
     */
    @TableId(type = IdType.AUTO)
    private Long applicationId;

    /**
     * 申请用户ID
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
     * 申请状态
     */
    private MerchantApplicationStatusEnum status;

    /**
     * 拒绝原因
     */
    private String rejectReason;

    /**
     * 审核人ID
     */
    private Long reviewedBy;

    /**
     * 审核时间
     */
    private LocalDateTime reviewedTime;
}
