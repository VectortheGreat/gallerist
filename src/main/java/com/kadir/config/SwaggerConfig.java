package com.kadir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Gallerist API")
                                                .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."))
                                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                                .components(new Components().addSecuritySchemes("bearerAuth",
                                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                                                .bearerFormat("JWT")
                                                                .description("JWT Bearer Token")));
        }
}