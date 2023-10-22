package com.juliocesark.gerenciador.tarefas.mapper.mock;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockTarefa {

    public Tarefa mockEntity() {
        return mockEntity(0);
    }
    
    public TarefaDTO mockDTO() {
    	return mockDTO(0);
    }
    
    public List<Tarefa> mockEntityList() {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        for (int i = 0; i < 14; i++) {
            tarefas.add(mockEntity(i));
        }
        return tarefas;
    }

    public List<TarefaDTO> mockDTOList() {
        List<TarefaDTO> tarefas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            tarefas.add(mockDTO(i));
        }
        return tarefas;
    }
    
    public Tarefa mockEntity(Integer number) {
    	Tarefa tarefa = new Tarefa();
        tarefa.setName("Tarefa" + number);
        tarefa.setOrder(number.longValue());
        tarefa.setPrice(new BigDecimal(number));
        tarefa.setId(number.longValue());

        LocalDate date = LocalDate.now().plusDays(number);
        tarefa.setLimitDate(date);
        return tarefa;
    }

    public TarefaDTO mockDTO(Integer number) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(number.longValue());
        tarefaDTO.setName("Tarefa" + number);
        tarefaDTO.setPrice(new BigDecimal(number).toString());
        LocalDate date = LocalDate.now().plusDays(number);
        tarefaDTO.setLimitDate(date);
        return tarefaDTO;
    }

}
