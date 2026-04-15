package com.jinHan.gold.core.todo.infra.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 待办业务事件 RabbitMQ 配置
 */
@Configuration
@ConditionalOnProperty(name = "todo.biz-event.mode", havingValue = "rabbit")
public class TodoRabbitConfig {

    @Bean
    public DirectExchange todoBizEventExchange(
            @Value("${todo.biz-event.rabbit.exchange:todo.biz.event.exchange}") String exchangeName) {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue todoBizEventQueue(
            @Value("${todo.biz-event.rabbit.queue:todo.biz.event.queue}") String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding todoBizEventBinding(
            Queue todoBizEventQueue,
            DirectExchange todoBizEventExchange,
            @Value("${todo.biz-event.rabbit.routing-key:todo.biz.event}") String routingKey) {
        return BindingBuilder.bind(todoBizEventQueue).to(todoBizEventExchange).with(routingKey);
    }

    @Bean
    public MessageConverter todoBizEventMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter todoBizEventMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(todoBizEventMessageConverter);
        return rabbitTemplate;
    }
}
