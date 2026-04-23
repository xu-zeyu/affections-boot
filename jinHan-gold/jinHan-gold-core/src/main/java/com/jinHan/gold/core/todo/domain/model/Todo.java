package com.jinHan.gold.core.todo.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 待办实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("todo_records")
public class Todo extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 待办业务类型，例如 ORDER_PAY / ORDER_SHIP
     */
    @TableField("biz_type")
    private String bizType;

    /**
     * 业务主键，例如订单号
     */
    @TableField("biz_id")
    private String bizId;

    private String title;

    private String content;

    @TableField("receiver_type")
    private TodoReceiverTypeEnum receiverType;

    @TableField("receiver_id")
    private Long receiverId;

    /**
     * 接收该待办所需的权限码，主要用于后台管理员待办分发与查询
     */
    @TableField("permission_code")
    private String permissionCode;

    private TodoStatusEnum status;

    @TableField("source_event")
    private String sourceEvent;

    @TableField("expire_time")
    private LocalDateTime expireTime;

    @TableField("completed_time")
    private LocalDateTime completedTime;
}
