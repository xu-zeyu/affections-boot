package com.jinHan.gold.core.fosterCare.domain.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类名: FosterCareStatusUpdateCommand
 * 描述: 更新寄养状态命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Data
public class FosterCareStatusUpdateCommand {
    
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
