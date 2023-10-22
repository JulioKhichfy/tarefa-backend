package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    TarefaService tarefaService;

    public void instantiateTestDatabase(){
        TarefaDTO t1 = new TarefaDTO();
        TarefaDTO t2 = new TarefaDTO();
        TarefaDTO t3 = new TarefaDTO();

        t1.setName("tarefa1");
        t1.setLimitDate(LocalDate.now().plusDays(1));
        String p1 = "100.50";
        t1.setPrice(p1);
        tarefaService.create(t1);

        t2.setName("tarefa2");
        t2.setLimitDate(LocalDate.now().plusDays(2));
        String p2 = "300.20";
        t2.setPrice(p2);
        tarefaService.create(t2);

        t3.setName("tarefa3");
        t3.setLimitDate(LocalDate.now().plusDays(3));
        String p3 = "50.40";
        t3.setPrice(p3);
        tarefaService.create(t3);
    }
}
