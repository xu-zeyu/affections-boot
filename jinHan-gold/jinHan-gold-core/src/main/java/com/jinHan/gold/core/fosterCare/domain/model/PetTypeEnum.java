package com.jinHan.gold.core.fosterCare.domain.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 类名: PetTypeEnum
 * 描述: 宠物类型枚举
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Getter
public enum PetTypeEnum {
    
    CAT("CAT", "猫"),
    DOG("DOG", "狗"),
    BIRD("BIRD", "鸟类"),
    RABBIT("RABBIT", "兔子"),
    OTHER("OTHER", "其他");
    
    @EnumValue
    @JsonValue
    private final String code;
    private final String description;
    
    PetTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static PetTypeEnum fromCode(String code) {
        for (PetTypeEnum type : PetTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown pet type code: " + code);
    }
}
