package com.jinHan.gold.admin.controller.variety.request;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.variety.domain.command.VarietyPageCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: VarietyPageRequest
 * 描述: 品种分页查询请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Data
public class VarietyPageRequest{
    /**
     * 页码，从1开始
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer size = 10;

    /**
     * 品种名称（模糊查询）
     */
    private String name;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;

    public VarietyPageCommand toCommand() {
        VarietyPageCommand command = new VarietyPageCommand();
        command.setPage(this.page);
        command.setSize(this.size);
        command.setName(this.name);
        command.setStatus(this.status);
        return command;
    }
}
