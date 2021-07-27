package com.example.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {
//    @Value("${spring.rabbitmq.host}")
//    private String host;

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }

//    @Bean
//    public void testBean() {
//        System.out.println("host:" + host);
//    }
}
