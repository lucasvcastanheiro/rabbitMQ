package org.springframework.amqp.tutorials.tut4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut4")
@Configuration
public class Config {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("tut.direct");
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
        public Binding binding1a(DirectExchange directExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(directExchange)
                    .with("orange");
        }

        @Bean()
        public Binding binding1b(DirectExchange directExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(directExchange)
                    .with("black");
        }

        @Bean()
        public Binding binding2a(DirectExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(directExchange)
                    .with("green");
        }

        @Bean()
        public Binding binding2b(DirectExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(directExchange)
                    .with("black");
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
