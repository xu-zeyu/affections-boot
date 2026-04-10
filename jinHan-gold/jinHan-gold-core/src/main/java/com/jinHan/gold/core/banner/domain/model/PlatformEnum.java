package com.jinHan.gold.core.banner.domain.model;


import lombok.Getter;

/**
 * 类名: PlatformEnum
 * 描述: 目标平台枚举
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */
@Getter
public enum PlatformEnum {

    ALL,
    PC,
    MOBILE;

    public boolean isPC() {
        return this == PC || this == ALL;
    }

    public boolean isMobile() {
        return this == MOBILE || this == ALL;
    }
}
