package com.ote.test;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {
 
    public Flux<Student> all() {
        RandomStringGenerator rsg = new RandomStringGenerator.Builder()
            .withinRange('a', 'z')
            .build();

        List<Student> studentList = IntStream.range(0, 10).
                mapToObj(i -> new Student(rsg.generate(10).toUpperCase(), RandomUtils.nextInt(18,40))).
                collect(Collectors.toList());

        return Flux.fromIterable(studentList);
    }
}