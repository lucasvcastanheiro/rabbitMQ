package org.springframework.amqp.tutorials.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "Hello")
public class Consumer {

    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [X] Received '" + in + "'");
    }
}
