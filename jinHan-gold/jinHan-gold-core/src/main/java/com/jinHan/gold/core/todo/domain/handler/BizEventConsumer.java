package com.jinHan.gold.core.todo.domain.handler;

import com.jinHan.gold.core.todo.domain.event.BizEvent;
import com.jinHan.gold.core.todo.domain.factory.TodoBuildResult;
import com.jinHan.gold.core.todo.domain.factory.TodoFactory;
import jakarta.annotation.Resource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 业务事件消费者
 */
@Component
public class BizEventConsumer {

    @Resource
    private TodoFactory todoFactory;

    @Resource
    private TodoService todoService;

    @EventListener
    public void onMessage(BizEvent event) {
        handle(event);
    }

    private void handle(BizEvent event) {
        TodoBuildResult result = todoFactory.build(event);
        result.getCompleteCommands().forEach(todoService::completeTodo);
        result.getCreateCommands().forEach(todoService::createTodo);
    }
}
