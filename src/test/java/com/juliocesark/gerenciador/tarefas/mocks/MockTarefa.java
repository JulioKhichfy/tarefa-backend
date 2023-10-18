package com.juliocesark.gerenciador.tarefas.mocks;


import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockTarefa {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public Tarefa mockEntity() {

        return mockEntity(0);
    }
    
    public TarefaDTO mockVO() {
    	return mockVO(0);
    }
    
    public List<Tarefa> mockEntityList() {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        for (int i = 0; i < 14; i++) {
            tarefas.add(mockEntity(i));
        }
        return tarefas;
    }

    public List<TarefaDTO> mockVOList() {
        List<TarefaDTO> tarefas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            tarefas.add(mockVO(i));
        }
        return tarefas;
    }
    
    public Tarefa mockEntity(Integer number) {
    	Tarefa tarefa = new Tarefa();
        tarefa.setName("nome da tarefa" + number);
        tarefa.setOrder(number.longValue());
        tarefa.setPrice(new BigDecimal(number));
        tarefa.setId(number.longValue());
        Instant instant = Instant.now().plus(Duration.ofDays(number));
        Date date = Date.from(instant);
        tarefa.setLimitDate(date);
        return tarefa;
    }

    public TarefaDTO mockVO(Integer number) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setName("nome da tarefa" + number);

        tarefaDTO.setPrice(new BigDecimal(number).toString());

        Instant instant = Instant.now().plus(Duration.ofDays(number));
        Date date = Date.from(instant);
        tarefaDTO.setLimitDate(formatter.format(date));
        return tarefaDTO;
    }

}
