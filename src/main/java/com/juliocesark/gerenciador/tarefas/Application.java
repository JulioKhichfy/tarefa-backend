package com.juliocesark.gerenciador.tarefas;

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
		Tarefa t1 = new Tarefa();
		Tarefa t2 = new Tarefa();
		Tarefa t3 = new Tarefa();

		t1.setName("Limpar piscina");
		t1.setLimitDate(new Date());
		BigDecimal p1 = new BigDecimal("100.50");
		t1.setPrice(p1);
		tarefaService.save(t1);

		t2.setName("Concertar ponte");
		t2.setLimitDate(new Date());
		BigDecimal p2 = new BigDecimal("300.20");
		t2.setPrice(p2);
		tarefaService.save(t2);

		t3.setName("Remover entulho");
		t3.setLimitDate(new Date());
		BigDecimal p3 = new BigDecimal("50.40");
		t3.setPrice(p3);
		tarefaService.save(t3);
	}
}
