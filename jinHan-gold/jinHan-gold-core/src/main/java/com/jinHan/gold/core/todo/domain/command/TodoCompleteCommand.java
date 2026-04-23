package com.jinHan.gold.core.todo.domain.command;

import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import lombok.Data;

/**
 * 完成待办命令
 */
@Data
public class TodoCompleteCommand {
    private String bizType;
    private String bizId;
    private TodoReceiverTypeEnum receiverType;
    private Long receiverId;
    private String permissionCode;
}
