package com.example.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "User Service API specifications for MSA",
                     description = "User Service API specifications with spring boot 3.2 + spring cloud.",
                     version ="v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customTestOpenAPI() {
        String[] paths = {"/users/**", "/welcome", "/health-check"};

        return GroupedOpenApi.builder()
                .group("일반 사용자 관리를 위한 User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}
