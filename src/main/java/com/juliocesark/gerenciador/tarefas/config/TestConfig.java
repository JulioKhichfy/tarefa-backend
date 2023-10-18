package com.juliocesark.gerenciador.tarefas.config;

import com.juliocesark.gerenciador.tarefas.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbservice;

    @Bean
    public boolean instantiateDatabase(){
        dbservice.instantiateTestDatabase();
        return true;
    }
}
