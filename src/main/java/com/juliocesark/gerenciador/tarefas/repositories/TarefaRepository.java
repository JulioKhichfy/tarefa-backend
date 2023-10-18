package com.juliocesark.gerenciador.tarefas.repositories;

import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}