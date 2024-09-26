package org.springframework.amqp.tutorials.tut5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut5")
@Configuration
public class Config {

    @Bean
    public TopicExchange directExchange() {
        return new TopicExchange("tut.topic");
    }

    @Profile("consumer")
    private static class ConsumerConfig {

        @Bean()
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean()
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean()
        public Binding binding1a(TopicExchange directExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(directExchange)
                    .with("*.orange.*");
        }

        @Bean()
        public Binding binding1b(TopicExchange directExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(directExchange)
                    .with("*.*.rabbit");
        }

        @Bean()
        public Binding binding2a(TopicExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(directExchange)
                    .with("lazy.#");
        }

        @Bean
        public Consumer consumer() {
            return new Consumer();
        }
    }

    @Profile("producer")
    @Bean
    public Producer producer() {
        return new Producer();
    }
}
