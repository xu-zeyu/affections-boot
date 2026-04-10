package com.jinHan.gold.core.auth.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 类名: IdCardInfo
 * 描述: 身份证认证信息表实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("id_card_info")
public class IdCardInfo extends BaseEntity {
    /**
     * 身份认证ID，主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 身份证号码
     */
    @TableField("id_number")
    private String idNumber;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 性别: M-男, F-女
     */
    @TableField("gender")
    private GenderEnum gender;

    /**
     * 有效期开始
     */
    @TableField("valid_start_date")
    private LocalDate validStartDate;

    /**
     * 有效期结束
     */
    @TableField("valid_end_date")
    private LocalDate validEndDate;

    /**
     * 审核状态
     */
    @TableField("audit_status")
    private AuditStatusEnum auditStatus;
}
