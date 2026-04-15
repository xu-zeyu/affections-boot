package com.jinHan.gold.core.order.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.log.annotation.Log;
import com.jinHan.gold.core.order.domain.command.OrderCreateCommand;
import com.jinHan.gold.core.order.domain.mapper.OrderItemMapper;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderItem;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.order.domain.model.PaymentMethodEnum;
import com.jinHan.gold.core.product.domain.command.ProductFindCommand;
import com.jinHan.gold.core.product.domain.handler.ProductFindCommandHandler;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.todo.domain.event.BizEventPublisher;
import com.jinHan.gold.core.todo.domain.event.BizEventTypeEnum;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 类名: OrderCreateHandler
 * 描述: 订单创建指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/14
 */
@Component
public class OrderCreateHandler {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ProductFindCommandHandler productFindCommandHandler;

    @Resource
    private BizEventPublisher bizEventPublisher;

    /**
     * 生成订单ID
     * 格式：时间戳（17位）+ 随机数（6位）
     * 示例：JH202601211234567890123456
     *
     * @return 唯一的订单ID
     */
    private String generateOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());

        // 生成6位随机数
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);

        // 组合成订单ID
        return "JH" + timestamp + randomNum;
    }

    @Transactional(rollbackFor =  Exception.class)
    @Log(value = "创建订单", operationType = "ORDER_CREATE")
    public String create(OrderCreateCommand command) {
        ProductFindCommand findCommand = new ProductFindCommand(command.getGoodsId());
        Product product = productFindCommandHandler.findProduct(findCommand);

        Orders orders = new Orders();

        // 生成并设置订单ID
        String orderId = generateOrderId();
        orders.setOrderId(orderId);
        orders.setUserId(command.getUserId());
        orders.setStatus(OrderStatusEnum.PENDING_PAYMENT);
        orders.setAddress("陕西省华阴市华山镇");
        orders.setTotalAmount(product.getPrice());
        orders.setPaymentMethod(PaymentMethodEnum.BANK);
        orders.setPaymentAmount(BigDecimal.valueOf(0));

        int inserted = orderMapper.insert(orders);
        if (inserted <= 0) {
            throw new BusinessException("下单失败");
        }

        // 创建订单商品明细
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setGoodsId(command.getGoodsId());
        orderItem.setProductName(product.getName());
        orderItem.setQuantity(command.getQuantity());
        orderItem.setFixedPrice(product.getPrice());

        // 保存订单商品明细
        int insert = orderItemMapper.insert(orderItem);
        if (insert <= 0) { throw new BusinessException("下单失败");}
        bizEventPublisher.publish(BizEventTypeEnum.ORDER_CREATED, orderId, command.getUserId());
        return orderId;
    }
}
