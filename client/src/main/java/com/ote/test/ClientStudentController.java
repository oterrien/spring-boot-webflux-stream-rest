package com.ote.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientStudentController {

    private WebClient client = WebClient.create("http://localhost:8080");

    @RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Student> findAll(@RequestParam(value = "pageSize", required = false) int pageSize) throws Exception {

        String uri = UriComponentsBuilder.
                fromPath("/students").
                queryParam("pageSize", pageSize).
                build().
                toUriString();

        return client.
                get().
                uri(uri).
                accept(MediaType.APPLICATION_STREAM_JSON).
                retrieve().
                bodyToFlux(Student.class);
    }
}