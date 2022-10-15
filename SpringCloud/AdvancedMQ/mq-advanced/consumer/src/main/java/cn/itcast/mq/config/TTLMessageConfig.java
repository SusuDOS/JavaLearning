package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class TTLMessageConfig {

    // ttl交换机队列声明和绑定:02-声明队列.png

    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Queue ttlQueue() {
        return QueueBuilder
                .durable("ttl.queue")
                .ttl(10000)
                .deadLetterExchange("dl.direct")
                .deadLetterRoutingKey("dl")
                .build();
    }

    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("ttl");
    }
}
