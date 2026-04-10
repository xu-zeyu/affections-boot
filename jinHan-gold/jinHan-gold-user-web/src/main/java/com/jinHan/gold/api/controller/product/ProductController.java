package com.jinHan.gold.api.controller.product;

import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.api.controller.product.request.ProductPageRequest;
import com.jinHan.gold.api.controller.product.response.ProductFindResponse;
import com.jinHan.gold.core.product.domain.command.ProductFindCommand;
import com.jinHan.gold.core.product.domain.handler.ProductFindCommandHandler;
import com.jinHan.gold.core.product.domain.handler.ProductPageCommandHandler;
import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.variety.domain.handler.VarietyQueryHandler;
import com.jinHan.gold.core.variety.domain.model.Variety;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import org.springframework.web.bind.annotation.*;
import com.jinHan.gold.core.product.domain.command.ProductPageCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类名: ProductController
 * 描述: 用户端商品接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/29
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductPageCommandHandler commandPageHandler;
    private final VarietyMapper varietyMapper;
    private final ProductFindCommandHandler commandFindHandler;
    private final VarietyQueryHandler varietyQueryHandler;

    ProductController(ProductPageCommandHandler commandPageHandler,
                     VarietyMapper varietyMapper,
                      ProductFindCommandHandler commandFindHandler,
                      VarietyQueryHandler varietyQueryHandler) {
        this.commandPageHandler = commandPageHandler;
        this.varietyMapper = varietyMapper;
        this.commandFindHandler = commandFindHandler;
        this.varietyQueryHandler = varietyQueryHandler;
    }

    /**
     * 分页查询宠物商品（用户端）
     */
    @GetMapping("/page")
    public Result<IPage<ProductFindResponse>> page(ProductPageRequest request) {
        ProductPageCommand command = request.toCommand();
        IPage<Product> productPage = commandPageHandler.page(command);

        // 提取所有品种ID
        List<Long> varietyIds = productPage.getRecords().stream()
                .map(Product::getVarietyType).toList();
        // 批量查询品种数据
        Map<Long, Variety> varietyMap;
        if (!varietyIds.isEmpty()) {
            List<Variety> varieties = varietyMapper.selectByIds(varietyIds);
            varietyMap = varieties.stream()
                    .collect(Collectors.toMap(Variety::getId, Function.identity()));
        } else {
            varietyMap = new HashMap<>();
        }

        IPage<ProductFindResponse> responsePage =  productPage.convert(product -> {
            ProductFindResponse response = new ProductFindResponse(product);

            // 从批量查询的结果中获取数据
            Variety variety = varietyMap.get(product.getVarietyType());
            if (variety != null) {
                response.setVariety(variety);
            }
            return response;
        });

        return Result.success(responsePage);
    }

    /**
     * 根据ID查询宠物商品
     */
    @GetMapping("/{id}")
    public Result<ProductFindResponse> findById(@PathVariable("id") Long id) {
        ProductFindCommand command = new ProductFindCommand(id);
        Product product = commandFindHandler.findProduct(command);
        ProductFindResponse response = new ProductFindResponse(product);
        Variety variety = varietyQueryHandler.findById(response.getVarietyType());
        response.setVariety(variety);
        return Result.success(response);
    }

}
