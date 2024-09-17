package org.springframework.amqp.tutorials.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1"})
@Configuration
public class Config {

    @Bean
    public Queue hello() {
        return new Queue("Hello");
    }

    @Profile("producer")
    @Bean
    public Producer producer() {
        return new Producer();
    }

    @Profile("consumer")
    @Bean
    public Consumer consumer() {
        return new Consumer();
    }
}
