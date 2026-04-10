package com.jinHan.gold.core.finance.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordQueryCommand;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import com.jinHan.gold.core.order.domain.command.OrderPageQueryCommand;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: incomeRecordQueryHandler
 * 描述: 收入指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/22
 */
@Component
public class IncomeRecordQueryHandler {
    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    /**
     * 分页查询订单列表
     */
    public IPage<IncomeRecord> queryPage(IncomeRecordQueryCommand command) {
        LambdaQueryWrapper<IncomeRecord> wrapper = new LambdaQueryWrapper<IncomeRecord>()
                .eq(command.getType() != null, IncomeRecord::getType, command.getType())
                .eq(command.getStatus() != null, IncomeRecord::getStatus, command.getStatus())
                .orderByDesc(IncomeRecord::getCreatedTime);

        return incomeRecordMapper.selectPage(new Page<>(command.getPage(), command.getSize()), wrapper);
    }
}
