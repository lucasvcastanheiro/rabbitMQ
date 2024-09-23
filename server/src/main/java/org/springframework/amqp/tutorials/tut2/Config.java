package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut2")
@Configuration
public class Config {

    @Bean
    public Queue hello() {
        return new Queue("Hello");
    }

    @Profile("consumer")
    private static class ConsumerConfig {

        @Bean
        public Consumer consumer1() {
            return new Consumer(1);
        }

        @Bean
        public Consumer consumer2() {
            return new Consumer(2);
        }

    }

    @Profile("producer")
    @Bean
    public Producer producer() {
        return new Producer();
    }
}
