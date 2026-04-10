package com.jinHan.gold.core.finance.domain.model;

import lombok.Getter;

/**
 * 类名: IncomeRecordTypeEnum
 * 描述: 收入记录类型枚举
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Getter
public enum IncomeRecordTypeEnum {
    PRODUCT_CAT_SALE("product_sale", "宠物猫销售"),
    PRODUCT_DOG_SALE("product_sale", "宠物狗销售"),
    SERVICE_FEE("service_fee", "宠物寄养服务");

    private final String type;
    private final String description;

    IncomeRecordTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
