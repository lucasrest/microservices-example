package com.rest.microservices.serverqueue.endpoint.sender;

import com.rest.microservices.core.model.Course;
import com.rest.microservices.core.utils.ObjectUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CourseSender {
    private final RabbitTemplate template;
    private final Queue queue;

    @Value("${queue.exchange-name}")
    private String exchange;

    public CourseSender(RabbitTemplate template,
                        @Qualifier("course") Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    public void insertSend(Course course) {
        this.template.convertAndSend(exchange, queue.getName(), ObjectUtils.objectToJson(course));
    }
}
