package org.springframework.amqp.tutorials.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumer1(String in) {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumer2(String in) {
        receive(in, 2);
    }

    public void receive(String in, int instance) {

        System.out.printf("Instance [%s] - [X] Received '%s'%n", instance, in);

    }
}
