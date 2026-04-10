package com.jinHan.gold.core.fosterCare.domain.command;

import lombok.Data;

/**
 * 类名: FosterCarePageQueryCommand
 * 描述: 分页查询寄养记录命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
public class FosterCarePageQueryCommand {
    
    /**
     * 当前页码
     */
    private Long page = 1L;
    
    /**
     * 每页数量
     */
    private Long size = 10L;
    
    /**
     * 宠物名称
     */
    private String petName;
    
    /**
     * 宠物类型
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
     * 寄养状态
     */
    private String status;
    
    /**
     * 寄养开始日期（查询开始）
     */
    private String fosterStartDateStart;
    
    /**
     * 寄养开始日期（查询结束）
     */
    private String fosterStartDateEnd;
}
