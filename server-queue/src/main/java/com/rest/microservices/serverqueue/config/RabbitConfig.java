package com.rest.microservices.serverqueue.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "queue")
public class RabbitConfig {

    private String exchangeName;

    private String routingKey;

    private String course;

    private String period;

    @Bean("course")
    Queue queueCourse() {
        return createQueue(course);
    }

    @Bean("period")
    Queue queuePeriod() {
        return createQueue(course);
    }

    @Bean
    Binding bindingCourse(@Qualifier("course") Queue queue) {
        return createBinding(queue, exchange(), routingKey.concat(".course.#"));
    }

    @Bean
    Binding bindingPeriod(@Qualifier("period") Queue queue) {
        return createBinding(queue, exchange(), routingKey.concat(".period.#"));
    }

    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    private Queue createQueue(String name) {
        return new Queue(routingKey.concat(".").concat(name), true);
    }

    private Binding createBinding(Queue queue, TopicExchange exchange, String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}
