package org.springframework.amqp.tutorials.tut2;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Producer {

    private final AtomicInteger messageCount = new AtomicInteger(0);

    private final AtomicInteger dots = new AtomicInteger(0);

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder messageBuilder = new StringBuilder("Hello");

        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        messageBuilder.append(".".repeat(Math.max(0, dots.get())));

        String finalMessage = String.format("%d - %s", messageCount.get(), messageBuilder.toString());

        template.convertAndSend(queue.getName(), finalMessage);

        System.out.printf(" [%d] Sent '%s'%n", messageCount.getAndIncrement(), finalMessage);
    }
}
