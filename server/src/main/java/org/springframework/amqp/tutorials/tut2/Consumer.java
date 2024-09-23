package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "Hello")
public class Consumer {

    private final int instance;

    public Consumer(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println(String.format("Instance [%s] - [X] Received '%s'", this.instance, in));

        doWork(in);

        stopWatch.stop();

        System.out.println(
                String.format("Instance [%s] - [X] Done in [%s]", this.instance, stopWatch.getTotalTimeSeconds()));
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }

    }
}
