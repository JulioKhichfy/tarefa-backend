package com.juliocesark.gerenciador.tarefas;

import com.juliocesark.gerenciador.tarefas.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception{
		Task t = new Task();
	}

}
