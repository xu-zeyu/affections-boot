package com.jinHan.gold.api.controller.order.request;

import com.jinHan.gold.core.auth.domain.command.UserAuthCommand;
import com.jinHan.gold.core.auth.domain.model.AuditStatusEnum;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.order.domain.command.OrderCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserAuthRequest
 * 描述: 订单创建请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class OrderCreateRequest {
    @NotNull(message = "商品不能为空")
    private Long goodsId;

    public OrderCreateCommand toCommand(Long userId) {
        OrderCreateCommand command = new OrderCreateCommand();
        command.setGoodsId(goodsId);
        command.setUserId(userId);
        return command;
    }
}
