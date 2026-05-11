package com.jinHan.gold.core.merchant.domain.command;

import lombok.Data;

/**
 * 类名: MerchantApplicationCreateCommand
 * 描述: 创建商家申请命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
public class MerchantApplicationCreateCommand {
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
}
