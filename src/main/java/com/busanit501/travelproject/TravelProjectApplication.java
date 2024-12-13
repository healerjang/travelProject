package com.busanit501.travelproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelProjectApplication.class, args);
    }

}
