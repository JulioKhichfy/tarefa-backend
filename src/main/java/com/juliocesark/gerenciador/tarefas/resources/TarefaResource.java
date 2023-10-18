package com.juliocesark.gerenciador.tarefas.resources;

import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService service;

    @GetMapping(value="/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Tarefa tarefa = service.find(id);
        return ResponseEntity.ok().body(tarefa);
    }
}