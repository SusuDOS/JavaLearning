package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            System.out.println(message + i);
            Thread.sleep(20);
        }
    }

    // 交换机给每一个queue发消息
    @Test
    public void testSendFanoutExchange() {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, every one!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    // 交换机根据关键字发给queue
    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "itcast.direct";
        // 消息
        String message = "hello, red!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    // 交换机根据top发到queue
    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "今天天气不错，我的心情好极了!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }

    // 发送object
    @Test
    public void testSendObjectQueue() {
        // 交换机名称
        String exchangeName = "object.queue";
        // 消息
        Map<String, Object> msg = new HashMap<>();
        // stringStringHashMap.put("姓名", "年龄");
        // stringStringHashMap.put("柳岩", "23");
        msg.put("name", "柳岩");
        msg.put("年龄", "23");

        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, msg);
    }

    // 发送String
    @Test
    public void testSendStringQueue() {
        // 交换机名称
        String exchangeName = "object.queue";
        // 消息
        Map<String, String> msg = new HashMap<>();
        // stringStringHashMap.put("姓名", "年龄");
        // stringStringHashMap.put("柳岩", "23");
        msg.put("name", "age");
        msg.put("柳岩", "23");

        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, msg);
    }
}
