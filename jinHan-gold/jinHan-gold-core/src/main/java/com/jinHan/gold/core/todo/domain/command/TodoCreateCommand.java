package com.jinHan.gold.core.todo.domain.command;

import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建待办命令
 */
@Data
public class TodoCreateCommand {
    private String bizType;
    private String bizId;
    private String title;
    private String content;
    private TodoReceiverTypeEnum receiverType;
    private Long receiverId;
    private String sourceEvent;
    private LocalDateTime expireTime;
}
