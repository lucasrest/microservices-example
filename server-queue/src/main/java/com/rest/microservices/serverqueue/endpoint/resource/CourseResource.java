package com.rest.microservices.serverqueue.endpoint.resource;

import com.rest.microservices.core.model.Course;
import com.rest.microservices.serverqueue.endpoint.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/admin/course")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseResource {

    private final CourseService service;

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course) {
        service.save(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
