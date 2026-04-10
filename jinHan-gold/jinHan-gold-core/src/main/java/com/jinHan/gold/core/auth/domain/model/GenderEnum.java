package com.jinHan.gold.core.auth.domain.model;

import lombok.Getter;

/**
 * 性别枚举
 */

@Getter
public enum GenderEnum {
    M("男"),
    F("女");

    private final String description;

    GenderEnum(String description) {
        this.description = description;
    }
}