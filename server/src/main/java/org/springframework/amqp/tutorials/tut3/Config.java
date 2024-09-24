package org.springframework.amqp.tutorials.tut3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut3")
@Configuration
public class Config {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
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
        public Binding binding1(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
        }

        @Bean()
        public Binding binding2(FanoutExchange fanoutExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanoutExchange);
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
