package com.rest.microservices.serverqueue;

import com.rest.microservices.serverqueue.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableRabbit
@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(value = RabbitConfig.class)
public class ServerQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerQueueApplication.class, args);
    }

}
