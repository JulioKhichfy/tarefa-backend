package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Tarefa find(Long id) {
        Optional<Tarefa> tarefa = repository.findById(id);

        if (!tarefa.isPresent())
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tarefa.class.getName());

        return tarefa.get(); // 200 OK
    }

    public void save(Tarefa tarefa) {
        tarefa.setOrder(getOrder());
        repository.save(tarefa);
    }

    private Long getOrder() {
        Long count = repository.count();
        if(count == null) return 1L;
        return count + 1;
    }
}