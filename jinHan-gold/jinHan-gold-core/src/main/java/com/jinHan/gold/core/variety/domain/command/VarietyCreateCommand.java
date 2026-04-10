package com.jinHan.gold.core.variety.domain.command;

import com.jinHan.gold.core.variety.domain.model.Variety;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: VarietyCreateCommand
 * 描述: 创建品种命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VarietyCreateCommand extends VarietyBaseCommand {
    /**
     * 将命令对象转换为实体对象
     */
    public Variety convertToEntity(VarietyCreateCommand command) {
        Variety variety = new Variety();
        variety.setName(command.getName());
        variety.setIconUrl(command.getIconUrl());
        variety.setMinPrice(command.getMinPrice());
        variety.setSortOrder(command.getSortOrder());
        variety.setStatus(command.getStatus());
        return variety;
    }
}
