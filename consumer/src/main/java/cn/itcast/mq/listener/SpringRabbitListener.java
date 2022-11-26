package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = {"simple.queue"})
    public void listenRabbitQueue(String msg) {
        System.out.println("消费者consumer 接收到 simple.queue的消息 " + msg );

    }

    @RabbitListener(queues = {"work.queue"})
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者consumer1 接收到 simple.queue的消息 " + msg + " " + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = {"work.queue"})
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.out.println("消费者consumer2 接收到 simple.queue的消息 " + msg + " " + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = {"fanout.queue1"})
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者consumer 接收到 fanout.queue1 的消息 " + msg + " " + LocalTime.now());
    }

    @RabbitListener(queues = {"fanout.queue2"})
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者consumer 接收到 fanout.queue2 的消息 " + msg + " " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "chow.direct", type = ExchangeTypes.DIRECT), // 默认direct
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者consumer, key = {\"red\", \"blue\"} 接收到 direct.queue1 的消息 " + msg + " " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "chow.direct", type = ExchangeTypes.DIRECT), // 默认direct
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg) {
        System.out.println("消费者consumer, key = {\"red\", \"yellow\"} 接收到 direct.queue2 的消息 " + msg + " " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "chow.topic", type = ExchangeTypes.TOPIC),
            key = {"china.*"}
    ))
    public void listenTopicQueue1(String msg) {
        System.out.println("消费者consumer, key = {\"china.*\"}接收到 direct.queue2 的消息 " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "chow.topic", type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenTopicQueue2(String msg) {
        System.out.println("消费者consumer, key = {\"*.news\"}  接收到 direct.queue2 的消息 " + msg);
    }

    @RabbitListener(queues = {"object.queue"})
    public void listenObjectQueue(Map<String, Object> msg) {
        System.out.println("接收到object.queue的消息: " + msg);
    }
}
