package com.jinHan.gold.core.todo.domain.handler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 待办过期扫描任务
 */
@Component
@ConditionalOnProperty(name = "todo.expire.enabled", havingValue = "true")
public class TodoExpireScheduler {

    private final TodoService todoService;

    public TodoExpireScheduler(TodoService todoService) {
        this.todoService = todoService;
    }

    @Scheduled(fixedDelayString = "${todo.expire.fixed-delay-ms:60000}")
    public void expireTodos() {
        todoService.expirePendingTodos();
    }
}
