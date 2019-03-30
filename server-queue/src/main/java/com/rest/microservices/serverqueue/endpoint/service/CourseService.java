package com.rest.microservices.serverqueue.endpoint.service;

import com.rest.microservices.core.model.Course;
import com.rest.microservices.serverqueue.endpoint.sender.CourseSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {
    private final CourseSender courseSender;

    public void save(Course course) {
        courseSender.insertSend(course);
    }
}