package com.jinHan.gold.core.order.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 类名: OrderItem
 * 描述: 订单详情
 * 作者: xuzeyu
 * 创建时间: 2026/1/21
 */
@Data
@TableName("order_item")
public class OrderItem {

    @TableId(type = IdType.AUTO)
    private Long itemId;

    @TableField("order_id")
    private String orderId;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("product_name")
    private String productName;

    @TableField("quantity")
    private Integer quantity;

    @TableField("fixed_price")
    private BigDecimal fixedPrice;

    @TableField("subtotal")
    private BigDecimal subtotal;
}
