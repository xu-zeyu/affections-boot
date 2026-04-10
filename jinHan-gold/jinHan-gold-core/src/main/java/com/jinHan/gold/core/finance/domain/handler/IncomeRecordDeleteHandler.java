package com.jinHan.gold.core.finance.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: IncomeRecordDeleteHandler
 * 描述: 收入记录删除指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Component
public class IncomeRecordDeleteHandler {

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        // 检查记录是否存在
        if (incomeRecordMapper.selectById(id) == null) {
            throw new BusinessException("收入记录不存在");
        }

        // 删除记录
        int deleted = incomeRecordMapper.deleteById(id);
        if (deleted <= 0) {
            throw new BusinessException("删除收入记录失败");
        }

        return true;
    }
}