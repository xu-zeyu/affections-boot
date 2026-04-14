package com.jinHan.gold.core.order.domain.command;

import com.jinHan.gold.core.order.domain.model.PaymentMethodEnum;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 类名: OrderPayCommand
 * 描述: 订单支付命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
public class OrderPayCommand {
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
    
    @NotNull(message = "支付方式不能为空")
    private PaymentMethodEnum paymentMethod;

    /**
     * 凭证URL
     */
    private String evidence;

    /**
     * 备注
     */
    private String note;

}
