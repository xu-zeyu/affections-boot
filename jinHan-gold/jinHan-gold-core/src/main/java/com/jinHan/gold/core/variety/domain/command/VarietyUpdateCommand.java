package com.jinHan.gold.core.variety.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: VarietyUpdateCommand
 * 描述: 更新品种命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VarietyUpdateCommand extends VarietyBaseCommand {
    /**
     * 品种ID
     */
    private Long id;
}
