package com.jinHan.gold.core.merchant.domain.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类名: MerchantApplicationStatusEnum
 * 描述: 商家申请状态枚举
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Getter
@AllArgsConstructor
public enum MerchantApplicationStatusEnum {
    PENDING("PENDING", "待审核"),
    APPROVED("APPROVED", "已通过"),
    REJECTED("REJECTED", "已拒绝");

    @EnumValue
    @JsonValue
    private final String code;
    private final String description;
}
