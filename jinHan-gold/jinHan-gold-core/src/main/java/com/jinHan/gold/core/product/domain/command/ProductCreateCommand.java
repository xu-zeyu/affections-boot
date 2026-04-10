package com.jinHan.gold.core.product.domain.command;

import com.jinHan.gold.core.product.domain.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;

/**
 * 类名: ProductCreateCommand
 * 描述: 创建商品命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductCreateCommand extends ProductBaseCommand {
    /**
     * 将命令对象转换为实体对象
     */
    public Product convertToEntity(ProductCreateCommand command) {
        Product product = new Product();
        product.setName(command.getName());
        product.setVarietyType(command.getVarietyType());
        product.setOriginPrice(command.getOriginPrice());
        product.setPrice(command.getPrice());
        product.setActivityCategory(command.getActivityCategory());
        product.setStatus(command.getStatus());
        product.setIsExcellence(command.getIsExcellence());
        product.setIsShipFree(command.getIsShipFree());
        product.setTitle(command.getTitle());
        product.setGender(command.getGender());
        product.setBirthday(command.getBirthday());
        product.setDescription(command.getDescription());
        product.setMainImage(command.getMainImage());
        product.setImages(command.getImages());

        return product;
    }
}
