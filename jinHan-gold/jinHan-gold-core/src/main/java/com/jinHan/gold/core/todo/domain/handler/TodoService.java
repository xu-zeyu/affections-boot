package com.jinHan.gold.core.todo.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.todo.domain.command.TodoCompleteCommand;
import com.jinHan.gold.core.todo.domain.command.TodoCreateCommand;
import com.jinHan.gold.core.todo.domain.mapper.TodoMapper;
import com.jinHan.gold.core.todo.domain.model.Todo;
import com.jinHan.gold.core.todo.domain.model.TodoStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 待办服务
 */
@Component
public class TodoService {

    @Resource
    private TodoMapper todoMapper;

    @Resource
    private TodoNotifier todoNotifier;

    @Transactional(rollbackFor = Exception.class)
    public Todo createTodo(TodoCreateCommand command) {
        Todo existed = todoMapper.selectOneOpt(new LambdaQueryWrapper<Todo>()
                .eq(Todo::getBizType, command.getBizType())
                .eq(Todo::getBizId, command.getBizId())
                .eq(Todo::getReceiverType, command.getReceiverType())
                .eq(command.getReceiverId() != null, Todo::getReceiverId, command.getReceiverId())
                .eq(Todo::getStatus, TodoStatusEnum.PENDING))
            .orElse(null);
        if (existed != null) {
            return existed;
        }

        Todo todo = new Todo();
        todo.setBizType(command.getBizType());
        todo.setBizId(command.getBizId());
        todo.setTitle(command.getTitle());
        todo.setContent(command.getContent());
        todo.setReceiverType(command.getReceiverType());
        todo.setReceiverId(command.getReceiverId());
        todo.setStatus(TodoStatusEnum.PENDING);
        todo.setSourceEvent(command.getSourceEvent());
        todo.setExpireTime(command.getExpireTime());
        todoMapper.insert(todo);
        todoNotifier.notifyCreated(todo);
        return todo;
    }

    @Transactional(rollbackFor = Exception.class)
    public int completeTodo(TodoCompleteCommand command) {
        List<Todo> todos = todoMapper.selectList(new LambdaQueryWrapper<Todo>()
                .eq(Todo::getBizType, command.getBizType())
                .eq(Todo::getBizId, command.getBizId())
                .eq(Todo::getReceiverType, command.getReceiverType())
                .eq(command.getReceiverId() != null, Todo::getReceiverId, command.getReceiverId())
                .eq(Todo::getStatus, TodoStatusEnum.PENDING));
        for (Todo todo : todos) {
            todo.setStatus(TodoStatusEnum.COMPLETED);
            todo.setCompletedTime(LocalDateTime.now());
            todoMapper.updateById(todo);
            todoNotifier.notifyUpdated(todo);
        }
        return todos.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int expirePendingTodos() {
        List<Todo> todos = todoMapper.selectList(new LambdaQueryWrapper<Todo>()
                .eq(Todo::getStatus, TodoStatusEnum.PENDING)
                .isNotNull(Todo::getExpireTime)
                .le(Todo::getExpireTime, LocalDateTime.now()));
        for (Todo todo : todos) {
            todo.setStatus(TodoStatusEnum.EXPIRED);
            todoMapper.updateById(todo);
            todoNotifier.notifyUpdated(todo);
        }
        return todos.size();
    }
}
