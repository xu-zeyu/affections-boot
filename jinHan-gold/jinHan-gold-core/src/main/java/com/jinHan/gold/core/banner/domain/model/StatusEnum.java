package com.jinHan.gold.core.banner.domain.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;


@Getter
public enum StatusEnum {

    /*
    * 禁用
    * */
    HIDDEN,

    /*
    * 启用
    * */
    ENABLE;

    public boolean isEnable() {
        return this == ENABLE;
    }
}
