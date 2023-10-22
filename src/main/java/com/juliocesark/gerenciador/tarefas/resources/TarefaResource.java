package com.juliocesark.gerenciador.tarefas.resources;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService service;

    /*@GetMapping(value="/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Tarefa tarefa = service.find(id);
        return ResponseEntity.ok().body(tarefa);
    }*/

    @GetMapping(value="/{id}")
    public TarefaDTO find(@PathVariable Long id) {
        return service.find(id);
    }

    /*@PostMapping()
    public ResponseEntity<Void> insert(@RequestBody TarefaDTO tarefaDTO) {
        //Tarefa tarefaModel = service.fromDTO(tarefaDTO);
        tarefaModel = service.save(tarefaModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefaModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }*/

    @PostMapping
    public TarefaDTO insert(@RequestBody TarefaDTO tarefaDTO) {
        return service.create(tarefaDTO);
    }

    /*@PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@RequestBody TarefaDTO tarefaDTO, @PathVariable Long id) {
        Tarefa tarefa = service.fromDTO(tarefaDTO);
        tarefa.setId(id);
        service.update(tarefa);
        return ResponseEntity.noContent().build();
    }*/

    @PutMapping
    public TarefaDTO update(@RequestBody TarefaDTO tarefaDTO) {
        return service.update(tarefaDTO);
    }

    /*@DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    /*@GetMapping
    public ResponseEntity<List<TarefaDTO>> findAll() {
        List<Tarefa> list = service.findAll();
        List<Tarefa> listOrdenada = list.stream()
                .sorted(Comparator.comparingLong(Tarefa::getOrder).reversed())
                .toList();

        List<TarefaDTO> listDto = listOrdenada.stream().map(obj -> new TarefaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }*/
    @GetMapping
    public List<TarefaDTO> findAll() {
        return service.findAll();
    }

    @PutMapping(value="/{id1}/{id2}")
    public ResponseEntity<Void> trocaOrdem(@PathVariable Long id1, @PathVariable Long id2) {
        service.updateOrder(id1, id2);
        return ResponseEntity.noContent().build();
    }
}