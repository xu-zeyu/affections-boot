package com.jinHan.gold.core.todo.domain.event;

import com.affectionsboot.common.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 业务事件发布器
 */
@Slf4j
@Component
public class BizEventPublisher {

    private final ObjectProvider<RabbitTemplate> rabbitTemplateProvider;

    @Value("${todo.biz-event.mode:local}")
    private String mode;

    @Value("${todo.biz-event.rabbit.exchange:todo.biz.event.exchange}")
    private String exchange;

    @Value("${todo.biz-event.rabbit.routing-key:todo.biz.event}")
    private String routingKey;

    public BizEventPublisher(ObjectProvider<RabbitTemplate> rabbitTemplateProvider) {
        this.rabbitTemplateProvider = rabbitTemplateProvider;
    }

    public void publish(BizEventTypeEnum type, String bizId, Long operatorId) {
        BizEvent event = new BizEvent(type, bizId, operatorId, LocalDateTime.now());
        if ("rabbit".equalsIgnoreCase(mode)) {
            RabbitTemplate rabbitTemplate = rabbitTemplateProvider.getIfAvailable();
            if (rabbitTemplate != null) {
                rabbitTemplate.convertAndSend(exchange, routingKey, event);
                return;
            }
            log.warn("RabbitTemplate unavailable, fallback to local event publishing.");
        }
        SpringUtils.publishEvent(event);
    }
}
