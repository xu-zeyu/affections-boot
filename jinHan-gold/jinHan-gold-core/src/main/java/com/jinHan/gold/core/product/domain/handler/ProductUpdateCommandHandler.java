package com.jinHan.gold.core.product.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jinHan.gold.core.product.domain.command.ProductUpdateCommand;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类名: ProductUpdateCommandHandler
 * 描述: 商品更新命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class ProductUpdateCommandHandler {
    @Resource
    private ProductMapper productMapper;

    /**
     * 更新宠物商品
     *
     * @param command 更新商品的命令对象
     * @return 更新后的商品实体
     */
    @Transactional(rollbackFor = Exception.class)
    public Product update(ProductUpdateCommand command) {
        // 参数校验
        if (command == null) {
            throw new BusinessException("更新商品的参数不能为空");
        }

        if (command.getId() == null) {
            throw new BusinessException("商品ID不能为空");
        }

        // 校验商品是否存在
        Product existProduct = productMapper.selectById(command.getId());
        if (existProduct == null) {
            throw new BusinessException("商品不存在");
        }

        try {
            // 构建更新条件
            LambdaUpdateWrapper<Product> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Product::getId, command.getId())
                    .set(command.getName() != null, Product::getName, command.getName())
                    .set(command.getVarietyType() != null, Product::getVarietyType, command.getVarietyType())
                    .set(command.getOriginPrice() != null, Product::getOriginPrice, command.getOriginPrice())
                    .set(command.getPrice() != null, Product::getPrice, command.getPrice())
                    .set(command.getActivityCategory() != null, Product::getActivityCategory, command.getActivityCategory())
                    .set(command.getStatus() != null, Product::getStatus, command.getStatus())
                    .set(command.getIsExcellence() != null, Product::getIsExcellence, command.getIsExcellence())
                    .set(command.getIsShipFree() != null, Product::getIsShipFree, command.getIsShipFree())
                    .set(command.getTitle() != null, Product::getTitle, command.getTitle())
                    .set(command.getGender() != null, Product::getGender, command.getGender())
                    .set(command.getBirthday() != null, Product::getBirthday, command.getBirthday())
                    .set(command.getDescription() != null, Product::getDescription, command.getDescription())
                    .set(command.getMainImage() != null, Product::getMainImage, command.getMainImage())
                    .set(command.getImages() != null, Product::getImages, command.getImages());

            // 执行更新
            int result = productMapper.update(null, updateWrapper);
            if (result <= 0) {
                throw new BusinessException("更新商品失败");
            }

            return productMapper.selectById(command.getId());
        } catch (Exception e) {
            throw new BusinessException("更新商品失败：" + e.getMessage());
        }
    }
}
