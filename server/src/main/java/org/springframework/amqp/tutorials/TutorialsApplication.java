package org.springframework.amqp.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialsApplication.class, args);
    }

}
