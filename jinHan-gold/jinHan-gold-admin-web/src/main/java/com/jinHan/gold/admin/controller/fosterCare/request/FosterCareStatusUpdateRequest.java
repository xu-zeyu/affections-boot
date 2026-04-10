package com.jinHan.gold.admin.controller.fosterCare.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类名: FosterCareStatusUpdateRequest
 * 描述: 更新寄养状态请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
public class FosterCareStatusUpdateRequest {
    
    /**
     * 寄养记录ID
     */
    @NotNull(message = "寄养记录ID不能为空")
    private Long id;
    
    /**
     * 寄养状态
     */
    @NotNull(message = "寄养状态不能为空")
    private String status;
}
