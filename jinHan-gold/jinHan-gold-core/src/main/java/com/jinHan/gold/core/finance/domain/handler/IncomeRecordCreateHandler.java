package com.jinHan.gold.core.finance.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordCreateCommand;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: IncomeRecordCreateHandler
 * 描述: 收入记录创建指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Component
public class IncomeRecordCreateHandler {

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long create(IncomeRecordCreateCommand command) {
        IncomeRecord incomeRecord = new IncomeRecord();
        
        // 设置收入基本信息
        incomeRecord.setType(command.getType());
        incomeRecord.setMoney(command.getMoney());
        
        // 如果命令中有描述则使用命令中的，否则使用枚举的description
        if (command.getDescribed() != null && !command.getDescribed().trim().isEmpty()) {
            incomeRecord.setDescribed(command.getDescribed());
        } else if (command.getType() != null) {
            incomeRecord.setDescribed(command.getType().getDescription());
        }
        
        incomeRecord.setOrderId(command.getOrderId());
        incomeRecord.setEvidenceUrl(command.getEvidenceUrl());
        
        // 设置默认状态为待审核
        incomeRecord.setStatus(IncomeRecordStatusEnum.PENDING_REVIEW);
        incomeRecord.setCreatedTime(LocalDateTime.now());

        // 插入数据库
        int inserted = incomeRecordMapper.insert(incomeRecord);
        if (inserted <= 0) {
            throw new BusinessException("创建收入记录失败");
        }

        return incomeRecord.getId();
    }
}
