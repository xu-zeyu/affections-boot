package com.jinHan.gold.core.shipping.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: ShippingPageQueryCommand
 * 描述: 分页查询发货记录命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShippingPageQueryCommand extends PageParam {
    private String orderId;
    private String logisticsNo;
    private String status;
}