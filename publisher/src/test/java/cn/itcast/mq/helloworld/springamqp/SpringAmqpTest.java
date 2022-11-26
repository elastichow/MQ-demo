package cn.itcast.mq.helloworld.springamqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RabbitAdmin rabbitAdmin;

    @Test
    public void testSendMessageSimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp";
        rabbitTemplate.convertAndSend(queueName, message);
    }


    @Test
    public void testSendMessageWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        rabbitAdmin.declareQueue(new Queue(queueName, false,false, false, null));
        String message = "hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }
}
