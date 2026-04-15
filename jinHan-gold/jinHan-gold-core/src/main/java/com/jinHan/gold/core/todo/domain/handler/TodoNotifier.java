package com.jinHan.gold.core.todo.domain.handler;

import com.jinHan.gold.core.todo.domain.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 待办消息推送器
 */
@Component
@RequiredArgsConstructor
public class TodoNotifier {

    private final ObjectProvider<SimpMessagingTemplate> messagingTemplateProvider;

    public void notifyCreated(Todo todo) {
        send("CREATED", todo);
    }

    public void notifyUpdated(Todo todo) {
        send("UPDATED", todo);
    }

    private void send(String action, Todo todo) {
        SimpMessagingTemplate messagingTemplate = messagingTemplateProvider.getIfAvailable();
        if (messagingTemplate == null) {
            return;
        }

        TodoNoticeMessage message = TodoNoticeMessage.from(action, todo);
        String receiverPath = todo.getReceiverType().name().toLowerCase();
        messagingTemplate.convertAndSend("/topic/todo/" + receiverPath, message);
        if (todo.getReceiverId() != null) {
            messagingTemplate.convertAndSend("/topic/todo/" + receiverPath + "/" + todo.getReceiverId(), message);
        }
    }
}
