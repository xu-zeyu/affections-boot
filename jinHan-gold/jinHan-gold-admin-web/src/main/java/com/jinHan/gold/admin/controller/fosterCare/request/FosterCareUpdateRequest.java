package com.jinHan.gold.admin.controller.fosterCare.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: FosterCareUpdateRequest
 * 描述: 更新寄养记录请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
public class FosterCareUpdateRequest {
    
    /**
     * 寄养记录ID
     */
    @NotNull(message = "寄养记录ID不能为空")
    private Long id;
    
    /**
     * 宠物名称
     */
    private String petName;
    
    /**
     * 宠物类型（猫、狗等）
     */
    private String petType;
    
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
     * 寄养单价（元/天）
     */
    private BigDecimal fosterPrice;
    
    /**
     * 寄养地址
     */
    private String fosterAddress;
    
    /**
     * 特殊要求
     */
    private String specialRequirements;
    
    /**
     * 备注
     */
    private String remark;
}
