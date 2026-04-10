package com.jinHan.gold.core.variety.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类名: VarietyDeleteCommand
 * 描述: 删除品种命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
@AllArgsConstructor
public class VarietyDeleteCommand {
    /**
     * 品种ID
     */
    private Long id;
}
