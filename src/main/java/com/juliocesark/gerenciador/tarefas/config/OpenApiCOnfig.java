package com.juliocesark.gerenciador.tarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiCOnfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API com Java 17 e Spring Boot 3 para Gerenciar Lista de Tarefas")
                        .version("v1")
                        .description("Lista de tarefas")
                        .termsOfService("")
                        .license(
                                new License()
                                        .name("julio cesar khichfy")
                                        .url("")
                        )
                );
    }
}
