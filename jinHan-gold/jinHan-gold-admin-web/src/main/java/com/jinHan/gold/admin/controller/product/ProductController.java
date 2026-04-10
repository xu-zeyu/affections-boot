package com.jinHan.gold.admin.controller.product;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.admin.controller.product.request.ProductCreateRequest;
import com.jinHan.gold.admin.controller.product.request.ProductPageRequest;
import com.jinHan.gold.admin.controller.product.request.ProductUpdateRequest;
import com.jinHan.gold.admin.controller.product.response.ProductFindResponse;
import com.jinHan.gold.core.product.domain.command.*;
import com.jinHan.gold.core.product.domain.handler.*;
import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.variety.domain.handler.VarietyQueryHandler;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类名: ProductController
 * 描述: 管理后台商品接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductCreateCommandHandler commandCreateHandler;
    private final ProductFindCommandHandler commandFindHandler;
    private final ProductUpdateCommandHandler commandUpdateHandler;
    private final ProductDeleteCommandHandler commandDeleteHandler;
    private final ProductPageCommandHandler commandPageHandler;
    private final VarietyQueryHandler varietyQueryHandler;

    private final VarietyMapper varietyMapper;

    ProductController(ProductCreateCommandHandler commandCreateHandler,
                     ProductFindCommandHandler commandFindHandler,
                     ProductUpdateCommandHandler commandUpdateHandler,
                     ProductDeleteCommandHandler commandDeleteHandler,
                     ProductPageCommandHandler commandPageHandler,
                      VarietyQueryHandler varietyQueryHandler,
                      VarietyMapper varietyMapper) {
        this.commandCreateHandler = commandCreateHandler;
        this.commandFindHandler = commandFindHandler;
        this.commandUpdateHandler = commandUpdateHandler;
        this.commandDeleteHandler = commandDeleteHandler;
        this.commandPageHandler = commandPageHandler;
        this.varietyQueryHandler = varietyQueryHandler;
        this.varietyMapper = varietyMapper;
    }

    /**
     * 创建宠物商品
     */
    @Log(value = "创建宠物商品", operationType = "PRODUCT_CREATE")
    @SaCheckPermission("PRODUCT_LIST_CREATE")
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid ProductCreateRequest request) {
        // 转换商品命令
        ProductCreateCommand productCommand = request.toCommand();

        return Result.success(commandCreateHandler.create(productCommand));
    }

    /**
     * 根据ID查询宠物商品
     */
    @Log(value = "查询宠物商品详情", operationType = "PRODUCT_GET")
    @GetMapping("/{id}")
    public Result<ProductFindResponse> findById(@PathVariable("id") Long id) {
        ProductFindCommand command = new ProductFindCommand(id);
        Product product = commandFindHandler.findProduct(command);
        ProductFindResponse response = new ProductFindResponse(product);
        Variety variety = varietyQueryHandler.findById(response.getVarietyType());
        response.setVariety(variety);
        return Result.success(response);
    }

    /**
     * 更新宠物商品
     */
    @Log(value = "更新宠物商品", operationType = "PRODUCT_UPDATE")
    @SaCheckPermission("PRODUCT_LIST_UPDATE")
    @PutMapping("/update")
    public Result<Product> update(@RequestBody @Valid ProductUpdateRequest request) {
        // 转换商品命令
        ProductUpdateCommand productCommand = request.toCommand();

        return Result.success(commandUpdateHandler.update(productCommand));
    }

    /**
     * 删除商品
     */
    @Log(value = "删除宠物商品", operationType = "PRODUCT_DELETE")
    @SaCheckPermission("PRODUCT_LIST_DELETE")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        ProductFindCommand command = new ProductFindCommand(id);
        return Result.success(commandDeleteHandler.deleteProduct(command));
    }

    /**
     * 分页查询宠物商品
     */
    @Log(value = "分页查询宠物商品", operationType = "PRODUCT_PAGE")
    @SaCheckPermission("PRODUCT_LIST")
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
}
