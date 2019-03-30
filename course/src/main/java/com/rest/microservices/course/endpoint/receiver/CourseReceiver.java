package com.rest.microservices.course.endpoint.receiver;

import com.rest.microservices.core.model.Course;
import com.rest.microservices.core.utils.ObjectUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CourseReceiver {

    @RabbitListener(queues = {"${queue.course}"})
    public void course(@Payload String course) {
        System.out.println(ObjectUtils.jsonToObject(course, Course.class).toString());
    }
}
