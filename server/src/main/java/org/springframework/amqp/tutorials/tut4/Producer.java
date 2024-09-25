package org.springframework.amqp.tutorials.tut4;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Producer {

    private final AtomicInteger messageCount = new AtomicInteger(0);

    private final AtomicInteger index = new AtomicInteger(0);

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitTemplate template;

    private final List<String> keys = List.of("orange", "black", "green");

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder messageBuilder = new StringBuilder("Hello to ");

        if (index.incrementAndGet() == 3) {
            index.set(0);
        }

        String key = keys.get(index.get());

        messageBuilder
                .append(key)
                .append(" ")
                .append(messageCount.get());

        String finalMessage = messageBuilder.toString();

        template.convertAndSend(directExchange.getName(), key, finalMessage);

        System.out.printf(" [%d] Sent '%s'%n", messageCount.getAndIncrement(), finalMessage);
    }
}
