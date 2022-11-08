package com.example.testnio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class TestNioApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestNioApplication.class, args);
    }

}
