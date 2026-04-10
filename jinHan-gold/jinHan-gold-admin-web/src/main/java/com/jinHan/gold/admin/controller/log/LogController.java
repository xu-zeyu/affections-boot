package com.jinHan.gold.admin.controller.log;

import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.affectionsboot.log.entity.LogEntity;
import com.affectionsboot.starter.mybatis.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.log.domain.command.LogDeleteCommand;
import com.jinHan.gold.core.log.domain.command.LogPageCommand;
import com.jinHan.gold.core.log.domain.handler.LogDeleteHandler;
import com.jinHan.gold.core.log.domain.handler.LogQueryHandler;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制器
 */
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Resource
    private LogQueryHandler logQueryHandler;
    
    @Resource
    private LogDeleteHandler logDeleteHandler;

    /**
     * 分页查询日志
     */
    @PostMapping("/page")
    public Result<IPage<LogEntity>> pageQuery(@RequestBody LogPageCommand command) {
        IPage<LogEntity> result = logQueryHandler.pageQuery(command);
        return Result.success(result);
    }
    
    /**
     * 删除日志
     */
    @Log(value = "删除日志", operationType = "LOG_DELETE")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody LogDeleteCommand command) {
        logDeleteHandler.delete(command);
        return Result.success(true);
    }
}
