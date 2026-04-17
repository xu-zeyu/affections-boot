package com.jinHan.gold.admin.controller.todo;

import cn.dev33.satoken.stp.StpUtil;
import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.core.todo.domain.command.TodoPageQueryCommand;
import com.jinHan.gold.core.todo.domain.handler.TodoQueryHandler;
import com.jinHan.gold.core.todo.domain.model.Todo;
import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台待办接口
 */
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Resource
    private TodoQueryHandler todoQueryHandler;

    @GetMapping("/page")
    public Result<IPage<Todo>> page(@Valid TodoPageQueryCommand command) {
        command.setReceiverType(TodoReceiverTypeEnum.ADMIN);
        command.setReceiverId(Long.parseLong(StpUtil.getLoginId().toString()));
        return Result.success(todoQueryHandler.queryPage(command));
    }

    @GetMapping("/pending-count")
    public Result<Long> pendingCount() {
        TodoPageQueryCommand command = new TodoPageQueryCommand();
        command.setReceiverType(TodoReceiverTypeEnum.ADMIN);
        command.setReceiverId(Long.parseLong(StpUtil.getLoginId().toString()));
        return Result.success(todoQueryHandler.countPending(command));
    }
}
