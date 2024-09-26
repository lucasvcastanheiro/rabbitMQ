package org.springframework.amqp.tutorials.tut5;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Producer {

    private final AtomicInteger messageCount = new AtomicInteger(0);

    private final AtomicInteger index = new AtomicInteger(0);

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private RabbitTemplate template;

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder messageBuilder = new StringBuilder("Hello to ");

        if (index.incrementAndGet() == keys.length) {
            index.set(0);
        }

        String key = keys[index.get()];

        messageBuilder
                .append(key)
                .append(" ")
                .append(messageCount.get());

        String finalMessage = messageBuilder.toString();

        template.convertAndSend(topicExchange.getName(), key, finalMessage);

        System.out.printf(" [%d] Sent '%s'%n", messageCount.getAndIncrement(), finalMessage);
    }
}
