package com.jinHan.gold.core.finance.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordUpdateCommand;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: IncomeRecordUpdateHandler
 * 描述: 收入记录更新指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Component
public class IncomeRecordUpdateHandler {

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean update(IncomeRecordUpdateCommand command) {
        // 检查记录是否存在
        IncomeRecord existingRecord = incomeRecordMapper.selectById(command.getId());
        if (existingRecord == null) {
            throw new BusinessException("收入记录不存在");
        }

        // 更新记录
        IncomeRecord incomeRecord = new IncomeRecord();
        incomeRecord.setId(command.getId());
        
        if (command.getType() != null) {
            incomeRecord.setType(command.getType());
        }
        
        if (command.getMoney() != null) {
            incomeRecord.setMoney(command.getMoney());
        }
        
        // 如果命令中有描述则使用命令中的，否则使用枚举的description
        if (command.getDescribed() != null && !command.getDescribed().trim().isEmpty()) {
            incomeRecord.setDescribed(command.getDescribed());
        } else if (command.getType() != null) {
            incomeRecord.setDescribed(command.getType().getDescription());
        }
        
        if (command.getOrderId() != null) {
            incomeRecord.setOrderId(command.getOrderId());
        }
        
        if (command.getEvidenceUrl() != null) {
            incomeRecord.setEvidenceUrl(command.getEvidenceUrl());
        }
        
        incomeRecord.setUpdatedTime(LocalDateTime.now());

        // 更新数据库
        int updated = incomeRecordMapper.updateById(incomeRecord);
        if (updated <= 0) {
            throw new BusinessException("更新收入记录失败");
        }

        return true;
    }
}