package com.jinHan.gold.core.finance.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: IncomeRecord
 * 描述: 收入记录实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "income_records")
public class IncomeRecord extends BaseEntity {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 收入来源
     */
    private IncomeRecordTypeEnum type;

    /**
     * 收入金额
     */
    private BigDecimal money;

    /**
     * 收入来源的详细说明
     */
    @TableField(value = "described")
    private String described;

    /**
     * 关联订单ID（可选）
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 凭证URL
     */
    @TableField(value = "evidence_url")
    private String evidenceUrl;

    /**
     * 审核人ID
     */
    @TableField(value = "reviewed_by")
    private Long reviewedBy;

    /**
     * 审核时间
     */
    @TableField(value = "reviewed_time")
    private LocalDateTime reviewedTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private IncomeRecordStatusEnum status;
}
