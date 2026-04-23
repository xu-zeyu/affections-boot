package com.jinHan.gold.core.todo.domain.handler;

import com.jinHan.gold.core.todo.domain.model.Todo;
import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import com.jinHan.gold.core.admin.domain.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 待办消息推送器
 */
@Component
@RequiredArgsConstructor
public class TodoNotifier {

    private final ObjectProvider<SimpMessagingTemplate> messagingTemplateProvider;
    private final AdminMapper adminMapper;

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
        if (todo.getReceiverType() == TodoReceiverTypeEnum.ADMIN) {
            sendAdminMessage(messagingTemplate, message, receiverPath, todo);
            return;
        }

        messagingTemplate.convertAndSend("/topic/todo/" + receiverPath, message);
        if (todo.getReceiverId() != null) {
            messagingTemplate.convertAndSend("/topic/todo/" + receiverPath + "/" + todo.getReceiverId(), message);
        }
    }

    private void sendAdminMessage(SimpMessagingTemplate messagingTemplate, TodoNoticeMessage message, String receiverPath, Todo todo) {
        if (todo.getReceiverId() != null) {
            messagingTemplate.convertAndSend("/topic/todo/" + receiverPath + "/" + todo.getReceiverId(), message);
            return;
        }

        if (todo.getPermissionCode() != null && !todo.getPermissionCode().isBlank()) {
            List<Long> adminIds = adminMapper.selectAdminIdsByPermissionCode(todo.getPermissionCode());
            for (Long adminId : adminIds) {
                messagingTemplate.convertAndSend("/topic/todo/" + receiverPath + "/" + adminId, message);
            }
            messagingTemplate.convertAndSend("/topic/todo/" + receiverPath + "/permission/" + todo.getPermissionCode(), message);
            return;
        }

        messagingTemplate.convertAndSend("/topic/todo/" + receiverPath, message);
    }
}
