package com.jinHan.gold.core.shipping.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 类名: ShippingRecord
 * 描述: 发货记录实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "shipping_record")
public class ShippingRecord extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 订单号，关联订单
     */
    private String orderId;
    
    /**
     * 物流公司
     */
    private String logisticsCompany;
    
    /**
     * 物流单号
     */
    private String logisticsNo;
    
    /**
     * 发货地址
     */
    private String shippingAddress;
    
    /**
     * 发货时间
     */
    private LocalDateTime shipTime;
    
    /**
     * 发货状态
     */
    private ShippingStatusEnum status;
    
    /**
     * 备注
     */
    private String remark;
}