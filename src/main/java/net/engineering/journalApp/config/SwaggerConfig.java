package net.engineering.journalApp.config;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI journalAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Journal App API")
                        .description("REST APIs for managing users, journals, admin operations, Redis caching, and secure authentication.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Ishika Raj")
                                .email("ishika@example.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://your-github-link.com"));
    }
}