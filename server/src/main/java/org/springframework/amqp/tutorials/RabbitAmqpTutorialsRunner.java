package org.springframework.amqp.tutorials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitAmqpTutorialsRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Ready ... ");
    }
}
