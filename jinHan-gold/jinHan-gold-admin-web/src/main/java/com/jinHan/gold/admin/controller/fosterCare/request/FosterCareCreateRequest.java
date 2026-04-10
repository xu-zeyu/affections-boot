package com.jinHan.gold.admin.controller.fosterCare.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类名: FosterCareCreateRequest
 * 描述: 创建寄养记录请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
public class FosterCareCreateRequest {
    
    /**
     * 下单人 Id
     */
    @NotNull(message = "下单人Id不能为空")
    private Long userId;

    /**
     * 宠物名称
     */
    @NotBlank(message = "宠物名称不能为空")
    private String petName;
    
    /**
     * 宠物类型（猫、狗等）
     */
    @NotBlank(message = "宠物类型不能为空")
    private String petType;
    
    /**
     * 主人姓名
     */
    @NotBlank(message = "主人姓名不能为空")
    private String ownerName;
    
    /**
     * 主人联系电话
     */
    @NotBlank(message = "主人联系电话不能为空")
    private String ownerPhone;
    
    /**
     * 寄养开始日期
     */
    @NotNull(message = "寄养开始日期不能为空")
    private LocalDateTime fosterStartDate;
    
    /**
     * 寄养结束日期
     */
    @NotNull(message = "寄养结束日期不能为空")
    private LocalDateTime fosterEndDate;
    
    /**
     * 寄养单价（元/天）
     */
    @NotNull(message = "寄养单价不能为空")
    private BigDecimal fosterPrice;
    
    /**
     * 寄养地址
     */
    @NotBlank(message = "寄养地址不能为空")
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
