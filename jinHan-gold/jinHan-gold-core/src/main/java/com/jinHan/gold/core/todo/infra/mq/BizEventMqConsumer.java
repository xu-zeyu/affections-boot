package com.jinHan.gold.core.todo.infra.mq;

import com.jinHan.gold.core.todo.domain.event.BizEvent;
import com.jinHan.gold.core.todo.domain.factory.TodoBuildResult;
import com.jinHan.gold.core.todo.domain.factory.TodoFactory;
import com.jinHan.gold.core.todo.domain.handler.TodoService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 业务事件消费者
 */
@Component
@ConditionalOnProperty(name = "todo.biz-event.mode", havingValue = "rabbit")
public class BizEventMqConsumer {

    @Resource
    private TodoFactory todoFactory;

    @Resource
    private TodoService todoService;

    @RabbitListener(queues = "${todo.biz-event.rabbit.queue:todo.biz.event.queue}")
    public void onMqMessage(BizEvent event) {
        TodoBuildResult result = todoFactory.build(event);
        result.getCompleteCommands().forEach(todoService::completeTodo);
        result.getCreateCommands().forEach(todoService::createTodo);
    }
}
