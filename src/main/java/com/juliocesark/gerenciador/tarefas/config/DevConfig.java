package com.juliocesark.gerenciador.tarefas.config;

import com.juliocesark.gerenciador.tarefas.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbservice;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase(){
        if (!"create".equals(strategy)) {
            return false;
        }
        dbservice.instantiateTestDatabase();
        return true;
    }
}
