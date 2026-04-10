package com.jinHan.gold.core.log.domain.handler;

import com.affectionsboot.log.service.LogService;
import com.jinHan.gold.core.log.domain.command.LogDeleteCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 日志删除处理器
 */
@Component
public class LogDeleteHandler {

    @Resource
    private LogService logService;

    /**
     * 删除日志
     * @param command 删除命令
     */
    public void delete(LogDeleteCommand command) {
        if (command.getId() != null) {
            // 删除单个日志
            logService.deleteLog(command.getId());
        } else if (command.getIds() != null && !command.getIds().isEmpty()) {
            // 批量删除日志
            logService.deleteLogs(command.getIds());
        }
    }
}
