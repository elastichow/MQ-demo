package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalTime;

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
}
