package com.jinHan.gold.core.order.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.banner.domain.command.BaseCommand;
import lombok.Data;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import lombok.EqualsAndHashCode;

/**
 * 类名: OrderPageQueryCommand
 * 描述: 订单分页查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderPageQueryCommand extends PageParam {
    private OrderStatusEnum status;
    private String orderId;
    private Long userId;
}
