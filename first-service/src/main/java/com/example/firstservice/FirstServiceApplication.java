package com.example.firstservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstServiceApplication {
    @Value("${spring.rabbitmq.host}")
    private String host;

    public static void main(String[] args) {
        SpringApplication.run(FirstServiceApplication.class, args);
    }

    @Bean
    public void testBean() {
        System.out.println("host:" + host);
    }
}
