package com.jinHan.gold.core.todo.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.todo.domain.command.TodoPageQueryCommand;
import com.jinHan.gold.core.todo.domain.mapper.TodoMapper;
import com.jinHan.gold.core.todo.domain.model.Todo;
import com.jinHan.gold.core.todo.domain.model.TodoStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 待办查询处理器
 */
@Component
public class TodoQueryHandler {

    @Resource
    private TodoMapper todoMapper;

    public IPage<Todo> queryPage(TodoPageQueryCommand command) {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<Todo>()
                .eq(command.getStatus() != null, Todo::getStatus, command.getStatus())
                .eq(command.getReceiverType() != null, Todo::getReceiverType, command.getReceiverType())
                .eq(command.getReceiverId() != null, Todo::getReceiverId, command.getReceiverId())
                .eq(command.getBizType() != null, Todo::getBizType, command.getBizType())
                .orderByDesc(Todo::getCreatedTime);
        return todoMapper.selectPage(new Page<>(command.getPage(), command.getSize()), wrapper);
    }

    public long countPending(TodoPageQueryCommand command) {
        return todoMapper.selectCount(new LambdaQueryWrapper<Todo>()
                .eq(Todo::getStatus, TodoStatusEnum.PENDING)
                .eq(command.getReceiverType() != null, Todo::getReceiverType, command.getReceiverType())
                .eq(command.getReceiverId() != null, Todo::getReceiverId, command.getReceiverId())
                .eq(command.getBizType() != null, Todo::getBizType, command.getBizType()));
    }
}
