package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
}
