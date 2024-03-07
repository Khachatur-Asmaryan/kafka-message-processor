package com.messageprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDbWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDbWriterApplication.class, args);
    }

}
