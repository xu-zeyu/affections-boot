package com.jinHan.gold.core.product.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.product.domain.command.ProductCreateCommand;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: ProductCreateCommandHandler
 * 描述: 商品创建命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class ProductCreateCommandHandler {
    @Resource
    private ProductMapper productMapper;

    /**
     * 创建宠物商品
     *
     * @param command 创建宠物商品的命令对象
     * @return 创建的商品ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long create(ProductCreateCommand command) {
        // 参数校验
        if (command == null) {
            throw new BusinessException("创建商品的参数不能为空");
        }

        // 校验商品名称
        if (command.getName() == null || command.getName().trim().isEmpty()) {
            throw new BusinessException("宠物名称不能为空");
        }

        // 转换为 Product 实体
        Product product = command.convertToEntity(command);

        // 插入商品数据库
        int result = productMapper.insert(product);
        if (result <= 0) {
            throw new BusinessException("创建商品失败");
        }

        return product.getId();
    }
}
