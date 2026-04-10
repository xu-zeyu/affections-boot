package com.jinHan.gold.core.log.domain.handler;

import com.affectionsboot.log.entity.LogEntity;
import com.affectionsboot.log.service.LogService;
import com.affectionsboot.starter.mybatis.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.log.domain.command.LogPageCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志查询处理器
 */
@Component
public class LogQueryHandler {

    @Resource
    private LogService logService;

    /**
     * 分页查询日志
     */
    public IPage<LogEntity> pageQuery(LogPageCommand command) {
        LambdaQueryWrapper<LogEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(command.getOperator() != null, LogEntity::getOperator, command.getOperator())
                .eq(command.getSuccess() != null, LogEntity::getSuccess, command.getSuccess())
                .eq(command.getOperationType() != null, LogEntity::getOperationType, command.getOperationType())
                .eq(command.getRequestMethod() != null, LogEntity::getRequestMethod, command.getRequestMethod())
                .orderByDesc(LogEntity::getOperateTime);
        return logService.queryLogs(command,queryWrapper);
    }
}
