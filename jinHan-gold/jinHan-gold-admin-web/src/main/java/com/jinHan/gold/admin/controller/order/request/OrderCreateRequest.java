package com.jinHan.gold.admin.controller.order.request;

import com.jinHan.gold.core.order.domain.command.OrderCreateCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

/**
 * 类名: OrderCreateRequest
 * 描述: 订单创建请求类
 * 作者: xuzeyu
 * 创建时间: 2026/1/14
 */
@Data
public class OrderCreateRequest {
    // 商品id
    @NotNull(message = "未提交下单的商品")
    private Long goodsId;


    public OrderCreateCommand toCommand() {
        OrderCreateCommand command = new OrderCreateCommand();
        command.setGoodsId(this.goodsId);
        return command;
    }
}
