package com.jinHan.gold.core.product.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.product.domain.command.ProductFindCommand;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: ProductFindCommandHandler
 * 描述: 商品查询命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class ProductFindCommandHandler {
    @Resource
    private ProductMapper productMapper;

    /**
     * 根据ID查询商品
     *
     * @param command 查询商品的命令对象
     * @return 商品实体
     */
    public Product findProduct(ProductFindCommand command) {
        if (command == null || command.getId() == null) {
            throw new BusinessException("商品ID不能为空");
        }

        Product product = productMapper.selectById(command.getId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        }

        return product;
    }
}
