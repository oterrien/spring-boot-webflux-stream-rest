package com.ote.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Duration;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*    *//**
     * Send result with the stream
     * @return
     *//*
    @RequestMapping(value = "/students", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> findAll() {
        return studentService.all();
    }*/

    /**
     * Collect the flux into List before sending
     *
     * @return
     */
    @RequestMapping(value = "/students", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> findAll(@Positive @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageSize != null) {
            return studentService.all().buffer(pageSize).delayElements(Duration.ofSeconds(1)).flatMap(p -> Flux.fromIterable(p));
        } else {
            return studentService.all().buffer(1).delayElements(Duration.ofSeconds(1)).flatMap(p -> Flux.fromIterable(p));
        }
    }
}