package com.jinHan.gold.core.order.domain.command;

import lombok.Data;

/**
 * 类名: OrderCreateCommand
 * 描述: 订单创建命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/14
 */
@Data
public class OrderCreateCommand {

    // 商品 id
    private Long goodsId;

    // 用户 id
    private Long userId;

    // 购买数量
    private Integer quantity = 1;
}
