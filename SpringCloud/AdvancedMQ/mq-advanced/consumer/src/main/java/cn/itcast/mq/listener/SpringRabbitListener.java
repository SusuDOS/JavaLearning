package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringRabbitListener {

    //11111111111// 演示异常消息队列：none。auto等模式.

    // @RabbitListener(queues = "simple.queue")
    // public void listenSimpleQueue(String msg) {
    // log.debug("消费者接收到simple.queue的消息：【" + msg + "】");
    // System.out.println(1 / 0);
    // log.info("消费者处理消息成功！");
    // }

    //2222222222222//

    
    // 演示死信交换机,与异常交换机模式互斥;这两种方式只能二选一....

    // 必须保留一个，不然自定义的仲裁队列挂不到rabbitmq里面去.

    // tip Listen方式直接声明：交换机-队列
    // 01-声明死信交换机.png

    // @RabbitListener(bindings = @QueueBinding(
    // value = @Queue(name = "dl.queue", durable = "true"),
    // exchange = @Exchange(name = "dl.direct"),
    // key = "dl"))
    // public void listenDlQueue(String msg) {
    // log.info("消费者接收到了dl.queue的延迟消息");
    // }

    // 333333333//

    // 基于注解方式定义并且监听延迟消息队列

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "delay.queue", durable = "true"), 
        exchange = @Exchange(name = "delay.direct", delayed = "true"), 
        key = "delay"))
    public void listenDelayExchange(String msg) {
        log.info("消费者接收到了delay.queue的延迟消息");
    }
}
