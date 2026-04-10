package com.jinHan.gold.core.finance.domain.command;

import com.jinHan.gold.core.finance.domain.model.IncomeRecordTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: IncomeRecordCreateCommand
 * 描述: 收入记录创建命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Data
public class IncomeRecordCreateCommand {
    /**
     * 收入来源类型
     */
    private IncomeRecordTypeEnum type;

    /**
     * 收入金额
     */
    private BigDecimal money;

    /**
     * 收入来源的详细说明
     */
    private String described;

    /**
     * 关联订单ID（可选）
     */
    private String orderId;

    /**
     * 凭证URL（可选）
     */
    private String evidenceUrl;
}
