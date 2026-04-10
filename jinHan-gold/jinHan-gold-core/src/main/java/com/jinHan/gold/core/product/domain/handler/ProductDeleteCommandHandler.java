package com.jinHan.gold.core.product.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.product.domain.command.ProductFindCommand;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: ProductDeleteCommandHandler
 * 描述: 商品删除命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class ProductDeleteCommandHandler {
    @Resource
    private ProductMapper productMapper;


    /**
     * 删除商品
     *
     * @param command 删除商品的命令对象
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteProduct(ProductFindCommand command) {
        if (command == null || command.getId() == null) {
            throw new BusinessException("商品ID不能为空");
        }

        // 校验商品是否存在
        Product product = productMapper.selectById(command.getId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        }

        // 删除商品
        int result = productMapper.deleteById(command.getId());
        if (result <= 0) {
            throw new BusinessException("删除商品失败");
        }

        return true;
    }
}
