package com.juliocesark.gerenciador.tarefas.repositories;

import com.juliocesark.gerenciador.tarefas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
