package com.jinHan.gold.core.finance.domain.command;

import com.jinHan.gold.core.finance.domain.model.IncomeRecordTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 类名: IncomeRecordUpdateCommand
 * 描述: 收入记录更新命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Data
public class IncomeRecordUpdateCommand {
    
    @NotNull(message = "ID不能为空")
    private Long id;
    
    private IncomeRecordTypeEnum type;
    
    @PositiveOrZero(message = "金额必须大于等于0")
    private BigDecimal money;
    
    @Length(max = 500, message = "描述长度不能超过500个字符")
    private String described;
    
    private String orderId;
    
    private String evidenceUrl;
}