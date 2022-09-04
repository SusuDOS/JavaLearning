package cn.itcast.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbitListener {

/*    *//*自动处理机制 尝试次数耗尽则直接丢弃消息*//*
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        log.debug("消费者接收到simple.queue的消息：【" + msg + "】");
        System.out.println(1 / 0);
        log.info("消费者处理消息成功！");
    }*/
/*  // 死信交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dl.queue", durable = "true"),
            exchange = @Exchange(name = "dl.direct"),
            key = "dl"
    ))
    public void listenDlQueue(String msg) {
        log.info("消费者接收到了dl.queue的延迟消息");
    }*/
/*  // 插件方式，延迟交换机.
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "delay.queue", durable = "true"),
            exchange = @Exchange(name = "delay.direct", delayed = "true"),
            key = "delay"
    ))
    public void listenDelayExchange(String msg) {
        log.info("消费者接收到了delay.queue的延迟消息");
    }*/
}
