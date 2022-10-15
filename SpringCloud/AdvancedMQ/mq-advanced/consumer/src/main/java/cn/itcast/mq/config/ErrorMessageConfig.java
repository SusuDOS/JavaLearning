package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;

/*
none：rabbit会在消费者取了消息之后就删除消息；
auto：若消费者不设置重试则消费端直接将消息打回rabbit，若设置尝试次数，耗尽次数，消费端和rabbit中都不会存在消息.

本java文件就是实现:
指定消息处理:由消费端将无法处理的异常消息投递到交换机("error.direct")->队列("error.queue");

*/
// 普通bean方式声明: 交换机-队列
// @Configuration
public class ErrorMessageConfig {

    @Bean
    public DirectExchange errorMessageExchange() {
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("error.queue");
    }

    @Bean
    public Binding errorMessageBinding() {
        return BindingBuilder.bind(errorQueue()).to(errorMessageExchange()).with("error");
    }

    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }
}
