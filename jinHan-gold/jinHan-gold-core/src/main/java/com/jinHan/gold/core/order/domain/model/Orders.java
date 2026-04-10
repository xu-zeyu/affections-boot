package com.jinHan.gold.core.order.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 类名: Orders
 * 描述: 订单实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "orders")
public class Orders extends BaseEntity {
    /**
     * 订单号，主键
     */
    @TableId(type = IdType.INPUT)
    private String orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal paymentAmount;

    /**
     * 订单状态
     */
    private OrderStatusEnum status;

    /**
     * 支付方式
     */
    private PaymentMethodEnum paymentMethod;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 订单商品明细（非数据库字段）
     */
    @TableField(exist = false)
    private List<OrderItem> orderItems;
}