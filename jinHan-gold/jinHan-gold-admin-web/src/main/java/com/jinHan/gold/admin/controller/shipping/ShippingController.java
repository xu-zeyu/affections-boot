package com.jinHan.gold.admin.controller.shipping;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.admin.controller.shipping.request.ShippingCreateRequest;
import com.jinHan.gold.admin.controller.shipping.request.ShippingStatusUpdateRequest;
import com.jinHan.gold.admin.controller.shipping.request.ShippingUpdateRequest;
import com.jinHan.gold.core.shipping.domain.command.ShippingCreateCommand;
import com.jinHan.gold.core.shipping.domain.command.ShippingPageQueryCommand;
import com.jinHan.gold.core.shipping.domain.command.ShippingStatusUpdateCommand;
import com.jinHan.gold.core.shipping.domain.command.ShippingUpdateCommand;
import com.jinHan.gold.core.shipping.domain.handler.ShippingCreateHandler;
import com.jinHan.gold.core.shipping.domain.handler.ShippingQueryHandler;
import com.jinHan.gold.core.shipping.domain.handler.ShippingStatusUpdateHandler;
import com.jinHan.gold.core.shipping.domain.handler.ShippingUpdateHandler;
import com.jinHan.gold.core.shipping.domain.model.ShippingRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: ShippingController
 * 描述: 发货管理接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Validated
@RestController
@RequestMapping("/shipping")
public class ShippingController {
    
    private final ShippingCreateHandler shippingCreateHandler;
    private final ShippingQueryHandler shippingQueryHandler;
    private final ShippingUpdateHandler shippingUpdateHandler;
    private final ShippingStatusUpdateHandler shippingStatusUpdateHandler;

    public ShippingController(ShippingCreateHandler shippingCreateHandler,
                              ShippingQueryHandler shippingQueryHandler,
                              ShippingUpdateHandler shippingUpdateHandler,
                              ShippingStatusUpdateHandler shippingStatusUpdateHandler) {
        this.shippingCreateHandler = shippingCreateHandler;
        this.shippingQueryHandler = shippingQueryHandler;
        this.shippingUpdateHandler = shippingUpdateHandler;
        this.shippingStatusUpdateHandler = shippingStatusUpdateHandler;
    }

    /**
     * 创建发货记录
     */
    @Log(value = "创建发货记录", operationType = "SHIPPING_CREATE")
    @SaCheckPermission("SHIPPING_LIST_CREATE")
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid ShippingCreateRequest request) {
        ShippingCreateCommand command = new ShippingCreateCommand();
        command.setOrderId(request.getOrderId());
        command.setLogisticsCompany(request.getLogisticsCompany());
        command.setLogisticsNo(request.getLogisticsNo());
        command.setShippingAddress(request.getShippingAddress());
        command.setRemark(request.getRemark());
        
        return Result.success(shippingCreateHandler.create(command));
    }

    /**
     * 分页查询发货记录
     */
    @Log(value = "分页查询发货记录", operationType = "SHIPPING_PAGE")
    @SaCheckPermission("SHIPPING_LIST")
    @GetMapping("/page")
    public Result<IPage<ShippingRecord>> queryPage(@Valid ShippingPageQueryCommand command) {
        return Result.success(shippingQueryHandler.queryPage(command));
    }

    /**
     * 根据ID查询发货记录详情
     */
    @Log(value = "查询发货记录详情", operationType = "SHIPPING_GET")
    @SaCheckPermission("SHIPPING_LIST_VIEW")
    @GetMapping("/{id}")
    public Result<ShippingRecord> queryDetail(@PathVariable Long id) {
        return Result.success(shippingQueryHandler.queryDetail(id));
    }

    /**
     * 更新发货记录
     */
    @Log(value = "更新发货记录", operationType = "SHIPPING_UPDATE")
    @SaCheckPermission("SHIPPING_LIST_UPDATE")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Valid ShippingUpdateRequest request) {
        ShippingUpdateCommand command = new ShippingUpdateCommand();
        command.setId(request.getId());
        command.setLogisticsCompany(request.getLogisticsCompany());
        command.setLogisticsNo(request.getLogisticsNo());
        command.setShippingAddress(request.getShippingAddress());
        command.setRemark(request.getRemark());
        
        shippingUpdateHandler.update(command);
        return Result.success(null);
    }

    /**
     * 更新发货状态
     */
    @Log(value = "更新发货状态", operationType = "SHIPPING_STATUS_UPDATE")
    @SaCheckPermission("SHIPPING_LIST_STATUS_UPDATE")
    @PostMapping("/status/update")
    public Result<Void> updateStatus(@RequestBody @Valid ShippingStatusUpdateRequest request) {
        ShippingStatusUpdateCommand command = new ShippingStatusUpdateCommand();
        command.setId(request.getId());
        command.setStatus(request.getStatus());
        
        shippingStatusUpdateHandler.updateStatus(command);
        return Result.success(null);
    }
}
