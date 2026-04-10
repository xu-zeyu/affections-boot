package com.jinHan.gold.admin.controller.order;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.admin.controller.order.request.OrderCreateRequest;
import com.jinHan.gold.core.order.domain.command.*;
import com.jinHan.gold.core.order.domain.handler.*;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: OrderController
 * 描述: 订单接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/14
 */
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCreateHandler orderCreateHandler;
    private final OrderQueryHandler orderQueryHandler;
    private final OrderAffirmHandler orderAffirmHandler;
    private final OrderShipHandler orderShipHandler;
    private final OrderCancelHandler orderCancelHandler;
    private final OrderPayHandler orderPayHandler;

    public OrderController(OrderCreateHandler orderCreateHandler,
                           OrderQueryHandler orderQueryHandler,
                           OrderAffirmHandler orderAffirmHandler,
                           OrderShipHandler orderShipHandler,
                           OrderCancelHandler orderCancelHandler,
                           OrderPayHandler orderPayHandler) {
        this.orderCreateHandler = orderCreateHandler;
        this.orderQueryHandler = orderQueryHandler;
        this.orderAffirmHandler = orderAffirmHandler;
        this.orderShipHandler = orderShipHandler;
        this.orderCancelHandler = orderCancelHandler;
        this.orderPayHandler = orderPayHandler;
    }

    /**
     * 创建订单
     */
    @Log(value = "创建订单", operationType = "ORDER_CREATE")
    @SaCheckPermission("ORDER_LIST")
    @PostMapping("/create")
    public Result<String> create(@RequestBody @Valid OrderCreateRequest request) {
        // 转换商品命令
        if (request == null) {
            throw new BusinessException("参数错误");
        }

        OrderCreateCommand orderCreateCommand =  new OrderCreateCommand();
        orderCreateCommand.setGoodsId(request.getGoodsId());

        return Result.success(orderCreateHandler.create(orderCreateCommand));
    }

    /**
     * 分页查询订单列表
     */
    @Log(value = "分页查询订单列表", operationType = "ORDER_PAGE")
    @SaCheckPermission("ORDER_LIST")
    @GetMapping("/page")
    public Result<IPage<Orders>> queryPage(@Valid OrderPageQueryCommand command) {
        return Result.success(orderQueryHandler.queryPage(command));
    }

    /**
     * 根据订单ID查询订单详情
     */
    @Log(value = "查询订单详情", operationType = "ORDER_GET")
    @SaCheckPermission("ORDER_LIST_VIEW")
    @GetMapping("/{orderId}")
    public Result<Orders> queryDetail(@PathVariable String orderId) {
        return Result.success(orderQueryHandler.queryDetail(orderId));
    }

    /**
     * 确认订单
     */
    @Log(value = "确认订单", operationType = "ORDER_AFFIRM")
    @PostMapping("/affirm")
    public Result<Null> affirm(@RequestBody @Valid OrderAffirmCommand command) {
        orderAffirmHandler.affirm(command);
        return Result.success(null);
    }

    /**
     * 发货
     */
    @Log(value = "订单发货", operationType = "ORDER_SHIP")
    @SaCheckPermission("ORDER_LIST_SHIP")
    @PostMapping("/ship")
    public Result<Long> ship(@RequestBody @Valid OrderShipCommand command) {
        Long shipped = orderShipHandler.ship(command);
        return Result.success(shipped);
    }

    /**
     * 取消订单
     */
    @Log(value = "取消订单", operationType = "ORDER_CANCEL")
    @SaCheckPermission("ORDER_LIST_CANCEL")
    @PostMapping("/cancel")
    public Result<Null> cancel(@RequestBody @Valid OrderCancelCommand command) {
        orderCancelHandler.cancel(command);
        return Result.success(null);
    }

    /**
     * 订单付款
     */
    @Log(value = "订单付款", operationType = "ORDER_PAY")
    @SaCheckPermission("ORDER_LIST_PAY")
    @PostMapping("/pay")
    public Result<Null> payOrder(@RequestBody @Valid OrderPayCommand command) {
        orderPayHandler.pay(command);
        return Result.success(null);
    }
}
