package com.jinHan.gold.core.auth.domain.model;

import lombok.Getter;

 /**
 * 审核状态枚举
 */
@Getter
public enum AuditStatusEnum {
    PENDING("待审核"),
    APPROVED("审核通过"),
    REJECTED("审核拒绝");

    private final String description;

     AuditStatusEnum(String description) {
        this.description = description;
    }
}