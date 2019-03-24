package com.rest.microservices.course.endpoint.service;

import com.rest.microservices.core.model.Course;
import com.rest.microservices.core.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository repository;

    public Iterable<Course> findAll(Pageable pageable) {
        log.info("Listing all courses");
        return repository.findAll(pageable);
    }

}
