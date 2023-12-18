package com.example.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

//@OpenAPIDefinition(
//        info = @Info(title = "Swagger Demo API 명세서",
//                description = "Spring Boot로 개발하는 RESTful API 명세서 입니다.",
//                version = "v1.0.0"))
//@Configuration
//@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPi() {
        String[] paths = {"/users/**"};

        return GroupedOpenApi
                .builder()
                .group("일반 사용자를 위한 User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}