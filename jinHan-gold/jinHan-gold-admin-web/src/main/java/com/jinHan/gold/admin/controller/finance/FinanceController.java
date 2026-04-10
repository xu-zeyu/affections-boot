package com.jinHan.gold.admin.controller.finance;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.core.finance.domain.command.ConfirmPaymentCommand;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordCreateCommand;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordQueryCommand;
import com.jinHan.gold.core.finance.domain.command.IncomeRecordUpdateCommand;
import com.jinHan.gold.core.finance.domain.handler.ConfirmPaymentHandler;
import com.jinHan.gold.core.finance.domain.handler.IncomeRecordCreateHandler;
import com.jinHan.gold.core.finance.domain.handler.IncomeRecordQueryHandler;
import com.jinHan.gold.core.finance.domain.handler.IncomeRecordUpdateHandler;
import com.jinHan.gold.core.finance.domain.handler.IncomeRecordDeleteHandler;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: FinanceController
 * 描述: 财务控制器
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Validated
@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final ConfirmPaymentHandler confirmPaymentHandler;
    private final IncomeRecordQueryHandler incomeRecordQueryHandler;
    private final IncomeRecordCreateHandler incomeRecordCreateHandler;
    private final IncomeRecordUpdateHandler incomeRecordUpdateHandler;
    private final IncomeRecordDeleteHandler incomeRecordDeleteHandler;

    public FinanceController(ConfirmPaymentHandler confirmPaymentHandler, 
                           IncomeRecordQueryHandler incomeRecordQueryHandler,
                           IncomeRecordCreateHandler incomeRecordCreateHandler,
                           IncomeRecordUpdateHandler incomeRecordUpdateHandler,
                           IncomeRecordDeleteHandler incomeRecordDeleteHandler) {
        this.confirmPaymentHandler = confirmPaymentHandler;
        this.incomeRecordQueryHandler = incomeRecordQueryHandler;
        this.incomeRecordCreateHandler = incomeRecordCreateHandler;
        this.incomeRecordUpdateHandler = incomeRecordUpdateHandler;
        this.incomeRecordDeleteHandler = incomeRecordDeleteHandler;
    }

     /*
     * 分页查询收入列表
     * */
    @Log(value = "分页查询收入列表", operationType = "FINANCE_PAGE")
    @SaCheckPermission("FINANCE_INCOME_LIST")
    @GetMapping("/page")
    public Result<IPage<IncomeRecord>> queryPage(@Valid IncomeRecordQueryCommand command) {
        return Result.success(incomeRecordQueryHandler.queryPage(command));
    }

    /**
     * 创建收入记录
     */
    @Log(value = "创建收入记录", operationType = "FINANCE_CREATE")
    @SaCheckPermission("FINANCE_INCOME_CREATE")
    @PostMapping("/income")
    public Result<Long> createIncome(@RequestBody @Valid IncomeRecordCreateCommand command) {
        try {
            Long incomeId = incomeRecordCreateHandler.create(command);
            return Result.success(incomeId);
        } catch (BusinessException e) {
            return Result.error(e);
        }
    }

    /**
     * 确认付款（财务审核）
     * 流程：更新订单状态为已确认 + 更新收入记录状态为已确认
     */
    @Log(value = "确认付款", operationType = "FINANCE_CONFIRM_PAYMENT")
    @SaCheckPermission("FINANCE_INCOME_AUDIT")
    @PostMapping("/confirm-payment/{id}")
    public Result<String> confirmPayment(@RequestBody @Valid ConfirmPaymentCommand command, @PathVariable Long id) {
        command.setId(id);
        try {
            confirmPaymentHandler.confirm(command);
            return Result.success("付款审核通过，收入记录已创建");
        } catch (BusinessException e) {
            return Result.error(e);
        }
    }

    /**
     * 更新收入记录
     */
    @Log(value = "更新收入记录", operationType = "FINANCE_UPDATE")
    @SaCheckPermission("FINANCE_INCOME_UPDATE")
    @PutMapping("/income/{id}")
    public Result<Boolean> updateIncome(@PathVariable Long id, @RequestBody @Valid IncomeRecordUpdateCommand command) {
        try {
            command.setId(id);
            boolean updated = incomeRecordUpdateHandler.update(command);
            return Result.success(updated);
        } catch (BusinessException e) {
            return Result.error(e);
        }
    }

    /**
     * 删除收入记录
     */
    @Log(value = "删除收入记录", operationType = "FINANCE_DELETE")
    @SaCheckPermission("FINANCE_INCOME_DELETE")
    @DeleteMapping("/income/{id}")
    public Result<Boolean> deleteIncome(@PathVariable Long id) {
        try {
            boolean deleted = incomeRecordDeleteHandler.delete(id);
            return Result.success(deleted);
        } catch (BusinessException e) {
            return Result.error(e);
        }
    }
}
