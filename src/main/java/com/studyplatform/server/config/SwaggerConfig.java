package com.studyplatform.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI studyPlatformAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Collaborative Study Platform Taras and Viacheslav API")
                        .description("REST API documentation for the Study Platform project")
                        .version("1.0.0")
                );
    }
}

// For swagger just :::
