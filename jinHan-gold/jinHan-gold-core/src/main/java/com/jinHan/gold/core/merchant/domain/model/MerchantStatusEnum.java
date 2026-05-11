package com.jinHan.gold.core.merchant.domain.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类名: MerchantStatusEnum
 * 描述: 商家状态枚举
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Getter
@AllArgsConstructor
public enum MerchantStatusEnum {
    ACTIVE("ACTIVE", "正常"),
    SUSPENDED("SUSPENDED", "暂停"),
    CLOSED("CLOSED", "关闭");

    @EnumValue
    @JsonValue
    private final String code;
    private final String description;
}
