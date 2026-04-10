package com.jinHan.gold.core.variety.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: VarietyPageCommand
 * 描述: 品种分页查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VarietyPageCommand extends PageParam {
    /**
     * 品种名称（模糊查询）
     */
    private String name;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;
}
