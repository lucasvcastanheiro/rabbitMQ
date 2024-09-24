package org.springframework.amqp.tutorials.tut3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class Consumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumer1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumer2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int instance) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println(String.format("Instance [%s] - [X] Received '%s'", instance, in));

        doWork(in);

        stopWatch.stop();

        System.out.println(
                String.format("Instance [%s] - [X] Done in [%s]", instance, stopWatch.getTotalTimeSeconds()));
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }

    }
}
