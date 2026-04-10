package com.jinHan.gold.core.log.domain.command;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 日志删除命令
 */
@Data
@Accessors(chain = true)
public class LogDeleteCommand {

    /**
     * 单个日志ID
     */
    private Long id;

    /**
     * 批量日志ID列表
     */
    private List<Long> ids;
}
