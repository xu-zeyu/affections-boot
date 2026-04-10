package com.jinHan.gold.core.product.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名: ProductFindCommand
 * 描述: 查询商品命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFindCommand {
    /**
     * 商品ID
     */
    private Long id;
}
