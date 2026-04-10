package com.jinHan.gold.core.fosterCare.domain.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 类名: FosterCareStatusEnum
 * 描述: 寄养状态枚举
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Getter
public enum FosterCareStatusEnum {
    
    PENDING("PENDING", "待寄养"),
    FOSTERING("FOSTERING", "寄养中"),
    COMPLETED("COMPLETED", "已完成"),
    CANCELLED("CANCELLED", "已取消");
    
    @EnumValue
    @JsonValue
    private final String code;
    private final String description;
    
    FosterCareStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static FosterCareStatusEnum fromCode(String code) {
        for (FosterCareStatusEnum status : FosterCareStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}
