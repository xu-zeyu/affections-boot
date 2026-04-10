package com.jinHan.gold.core.finance.domain.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: ConfirmPaymentCommand
 * 描述: 确认付款命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
public class ConfirmPaymentCommand {
    /*
    * 收入 id
    * */

    private Long id;

    /**
     * 订单ID
     */
    @NotBlank(message = "订单ID不能为空")
    private String orderId;

    /**
     * 订单金额
     */
    @NotNull(message = "订单金额不能为空")
    private BigDecimal orderAmount;

    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private String paymentMethod;

    /**
     * 审核人ID
     */
    @NotNull(message = "审核人不能为空")
    private Long reviewedBy;
}
