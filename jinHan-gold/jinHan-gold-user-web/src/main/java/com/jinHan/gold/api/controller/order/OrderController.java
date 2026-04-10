package com.jinHan.gold.api.controller.order;

import com.affectionsboot.auth.stp.StpUserUtil;
import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.api.controller.order.request.OrderCreateRequest;
import com.jinHan.gold.core.order.domain.command.*;
import com.jinHan.gold.core.order.domain.handler.*;
import com.jinHan.gold.core.order.domain.model.Orders;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: OrderController
 * 描述: 订单接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/20
 */
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderCreateHandler orderCreateHandler;
    
    @Resource
    private OrderQueryHandler orderQueryHandler;
    
    @Resource
    private OrderCancelHandler orderCancelHandler;
    
    @Resource
    private OrderPayHandler orderPayHandler;

    @PostMapping("/create")
    public Result<String> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        OrderCreateCommand command = request.toCommand(Long.parseLong(StpUserUtil.getLoginId().toString()));
        return Result.success(orderCreateHandler.create(command));
    }

    /**
     * 分页查询用户订单列表
     */
    @GetMapping("/page")
        public Result<IPage<Orders>> queryUserOrders(@Valid OrderPageQueryCommand command) {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        command.setUserId(userId);
        return Result.success(orderQueryHandler.queryPage(command));
    }

    /**
     * 根据订单ID查询订单详情
     */
    @GetMapping("/{orderId}")
    public Result<Orders> queryOrderDetail(@PathVariable String orderId) {
        return Result.success(orderQueryHandler.queryDetail(orderId));
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public Result<Void> cancelOrder(@RequestBody @Valid OrderCancelCommand command) {
        orderCancelHandler.cancel(command);
        return Result.success();
    }

    /**
     * 订单付款
     */
    @PostMapping("/pay")
    public Result<Void> payOrder(@RequestBody @Valid OrderPayCommand command) {
        orderPayHandler.pay(command);
        return Result.success();
    }


    /**
     * 确认收货
     */
    @PostMapping("/complete/{orderId}")
    public Result<Void> completeOrder(@PathVariable String orderId) {
        // TODO: 创建 CompleteCommand 和 Handler
        return Result.success();
    }
}
