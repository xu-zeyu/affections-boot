package com.jinHan.gold.core.finance.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordStatusEnum;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: IncomRecordQueryCommon
 * 描述: 分页查询收入列表命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IncomeRecordQueryCommand extends PageParam {
    private IncomeRecordTypeEnum type;
    private IncomeRecordStatusEnum status;
}
