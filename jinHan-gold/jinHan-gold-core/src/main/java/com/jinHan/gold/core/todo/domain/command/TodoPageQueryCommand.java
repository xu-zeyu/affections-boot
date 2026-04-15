package com.jinHan.gold.core.todo.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.todo.domain.model.TodoReceiverTypeEnum;
import com.jinHan.gold.core.todo.domain.model.TodoStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 待办分页查询命令
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TodoPageQueryCommand extends PageParam {
    private TodoStatusEnum status;
    private TodoReceiverTypeEnum receiverType;
    private Long receiverId;
    private String bizType;
}
