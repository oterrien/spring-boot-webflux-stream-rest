package com.ote.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class ClientStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientStudentApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
          /* Flux<Student> stream = WebClient.
                   create("http://localhost:8080").
                   get().
                   uri("/students").
                   retrieve().
                   bodyToFlux(ServerSentEvent.class).
                   flatMap(e -> Mono.justOrEmpty(e.data())).
                   map(x -> (Map<String, String>) x).
                   map(m -> parse(m));

            stream.subscribe(m -> log.info(m.toString()));*/

            /*WebClient.
                    create("http://localhost:8080").
                    get().
                    uri("/students").
                    retrieve().
                    bodyToFlux(Student.class).
                    toStream().
                    forEach(p -> log.info(p.toString()));*/
        };
    }

    private static Student parse(Map<String, String> map) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, Student.class);
    }
}
