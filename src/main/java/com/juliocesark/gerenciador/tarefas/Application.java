package com.juliocesark.gerenciador.tarefas;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	TarefaService tarefaService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TarefaDTO t1 = new TarefaDTO();
		TarefaDTO t2 = new TarefaDTO();
		TarefaDTO t3 = new TarefaDTO();

		t1.setName("Limpar piscina");
		t1.setLimitDate("10/11/2023");
		String p1 = "100.50";
		t1.setPrice(p1);
		Tarefa tarefa1 = tarefaService.fromDTO(t1);
		tarefaService.save(tarefa1);

		t2.setName("Concertar ponte");
		t2.setLimitDate("09/11/2023");
		String p2 = "300.20";
		t2.setPrice(p2);
		Tarefa tarefa2 = tarefaService.fromDTO(t2);
		tarefaService.save(tarefa2);

		t3.setName("Remover entulho");
		t3.setLimitDate("08/12/2023");
		String p3 = "50.40";
		t3.setPrice(p3);
		Tarefa tarefa3 = tarefaService.fromDTO(t3);
		tarefaService.save(tarefa3);
	}
}
