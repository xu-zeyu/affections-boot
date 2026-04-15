package com.jinHan.gold.core.todo.domain.handler;

import com.jinHan.gold.core.todo.domain.model.Todo;
import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import com.jinHan.gold.core.todo.domain.model.TodoStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 待办推送消息
 */
@Data
public class TodoNoticeMessage {
    private String action;
    private Long todoId;
    private String bizType;
    private String bizId;
    private String title;
    private String content;
    private TodoReceiverTypeEnum receiverType;
    private Long receiverId;
    private TodoStatusEnum status;
    private LocalDateTime expireTime;

    public static TodoNoticeMessage from(String action, Todo todo) {
        TodoNoticeMessage message = new TodoNoticeMessage();
        message.setAction(action);
        message.setTodoId(todo.getId());
        message.setBizType(todo.getBizType());
        message.setBizId(todo.getBizId());
        message.setTitle(todo.getTitle());
        message.setContent(todo.getContent());
        message.setReceiverType(todo.getReceiverType());
        message.setReceiverId(todo.getReceiverId());
        message.setStatus(todo.getStatus());
        message.setExpireTime(todo.getExpireTime());
        return message;
    }
}
