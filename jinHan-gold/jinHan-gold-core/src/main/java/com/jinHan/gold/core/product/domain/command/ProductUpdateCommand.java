package com.jinHan.gold.core.product.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: ProductUpdateCommand
 * 描述: 更新商品命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductUpdateCommand extends ProductBaseCommand {
    /**
     * 商品ID
     */
    private Long id;
}
