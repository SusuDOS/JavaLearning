package cn.itcast.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 普通bean方式声明: 交换机-队列
// @Configuration
public class CommonConfig {
    @Bean
    public DirectExchange simpleDirect() {
        return new DirectExchange("simple.direct");
    }

    @Bean
    public Queue simpleQueue() {
        return QueueBuilder.durable("simple.queue").build();
    }
}
