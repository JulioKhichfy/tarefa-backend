package com.juliocesark.gerenciador.tarefas.resources;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import com.juliocesark.gerenciador.tarefas.util.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api/tarefas/v1")
@Tag(name = "Tarefa", description = "Endpoints para gerenciar tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService service;

    @GetMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON })
    public TarefaDTO find(@PathVariable Long id) {
        return service.find(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON })
    public TarefaDTO insert(@RequestBody TarefaDTO tarefaDTO) {
        return service.create(tarefaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON })
    public TarefaDTO update(@RequestBody TarefaDTO tarefaDTO) {
        return service.update(tarefaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON })
    public List<TarefaDTO> findAll() {
        return service.findAll();
    }

    @PutMapping(value="/{id1}/{id2}")
    public ResponseEntity<Void> trocarOrdem(@PathVariable Long id1, @PathVariable Long id2) {
        service.updateOrder(id1, id2);
        return ResponseEntity.noContent().build();
    }
}