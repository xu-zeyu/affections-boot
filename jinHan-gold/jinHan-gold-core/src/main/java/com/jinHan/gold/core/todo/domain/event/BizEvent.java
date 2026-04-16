package com.jinHan.gold.core.todo.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 业务事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizEvent {
    private BizEventTypeEnum type;
    private String bizId;
    private Long operatorId;
    private LocalDateTime occurredTime;
}
