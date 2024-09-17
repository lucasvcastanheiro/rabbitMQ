package org.springframework.amqp.tutorials.tut1;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class Producer {

    private final AtomicInteger messageCount = new AtomicInteger(0);

    @Value("${tutorial.client.message:default}")
    private String message;

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 3000, initialDelay = 500)
    public void send() {
        String finalMessage = String.format("%d - %s", messageCount.get(), message);

        this.template.convertAndSend(queue.getName(), finalMessage);
        System.out.printf(" [%d] Sent '%s'%n", messageCount.getAndIncrement(), finalMessage);
    }
}
