package com.busanit501.travelproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    public List<String> adminList() {
        return List.of("admin1", "admin2", "admin3");
    }
}
