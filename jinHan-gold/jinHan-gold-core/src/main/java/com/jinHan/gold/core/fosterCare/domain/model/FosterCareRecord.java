package com.jinHan.gold.core.fosterCare.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: FosterCareRecord
 * 描述: 寄养记录实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "foster_care_record")
public class FosterCareRecord extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 宠物ID
     */
    private Long userId;
    
    /**
     * 宠物名称
     */
    private String petName;
    
    /**
     * 宠物类型（猫、狗等）
     */
    private PetTypeEnum petType;
    
    /**
     * 主人姓名
     */
    private String ownerName;
    
    /**
     * 主人联系电话
     */
    private String ownerPhone;
    
    /**
     * 寄养开始日期
     */
    private LocalDateTime fosterStartDate;
    
    /**
     * 寄养结束日期
     */
    private LocalDateTime fosterEndDate;
    
    /**
     * 寄养天数
     */
    private Integer fosterDays;
    
    /**
     * 寄养单价（元/天）
     */
    private BigDecimal fosterPrice;
    
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 寄养地址
     */
    private String fosterAddress;
    
    /**
     * 特殊要求
     */
    private String specialRequirements;
    
    /**
     * 寄养状态
     */
    private FosterCareStatusEnum status;
    
    /**
     * 备注
     */
    private String remark;
}
