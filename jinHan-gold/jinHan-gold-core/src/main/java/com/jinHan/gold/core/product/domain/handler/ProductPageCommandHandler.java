package com.jinHan.gold.core.product.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.product.domain.command.ProductPageCommand;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: ProductPageCommandHandler
 * 描述: 商品分页查询命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class ProductPageCommandHandler {
    @Resource
    private ProductMapper productMapper;

    /**
     * 分页查询商品
     *
     * @param command 分页查询商品的命令对象
     * @return 商品分页结果
     */
    public IPage<Product> page(ProductPageCommand command) {
        // 构建查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();

        // 商品名称模糊查询
        if (StringUtils.isNotBlank(command.getName())) {
            queryWrapper.like(Product::getName, command.getName());
        }

        // 商品状态
        if (command.getStatus() != null) {
            queryWrapper.eq(Product::getStatus, command.getStatus());
        }

        // 品种
        if (command.getVarietyType() != null) {
            queryWrapper.eq(Product::getVarietyType, command.getVarietyType());
        }

        // 性别
        if (command.getGender() != null) {
            queryWrapper.eq(Product::getGender, command.getGender());
        }

        // 按排序字段升序，创建时间降序
        queryWrapper.orderByDesc(Product::getCreatedTime);

        // 分页查询
        return productMapper.selectPage(new Page<>(command.getPage(),command.getSize()), queryWrapper);
    }
}
