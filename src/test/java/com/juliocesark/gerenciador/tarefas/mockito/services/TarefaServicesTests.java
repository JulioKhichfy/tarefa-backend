package com.juliocesark.gerenciador.tarefas.mockito.services;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.mocks.MockTarefa;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import com.juliocesark.gerenciador.tarefas.service.exceptions.TaskNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TarefaServicesTests {

    MockTarefa input;

    @InjectMocks
    private TarefaService service;

    @Mock
    TarefaRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockTarefa();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Tarefa entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.find(1L);
        assertNotNull(result);
        assertNotNull(result.getName());
        assertNotNull(result.getPrice());
        assertNotNull(result.getLimitDate());
        assertEquals("nome da tarefa1", result.getName());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(TaskNameException.class, () -> {
            service.save(null);
        });
        String expectedMessage = "Tarefa nula";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testUpdate() {
        Tarefa entity = input.mockEntity(1);
        Tarefa persisted = entity;
        persisted.setId(1L);
        TarefaDTO dto = input.mockVO(1);
        dto.setName("OUTRA TAREFA");
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);
        Tarefa tarefa = service.fromDTO(dto);
        tarefa.setId(1L);
        var result = service.update(tarefa);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("OUTRA TAREFA", result.getName());
    }

    @Test
    void testDelete() {
        Tarefa entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }

    @Test
    void testFindAll() {
        List<Tarefa> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        var tarefas = service.findAll();
        assertNotNull(tarefas);
        assertEquals(14, tarefas.size());
        var tarefa1 = tarefas.get(1);

        assertNotNull(tarefa1);
        assertNotNull(tarefa1.getId());
        assertNotNull(tarefa1.getName());
        assertNotNull(tarefa1.getPrice());
        assertNotNull(tarefa1.getLimitDate());
        assertNotNull(tarefa1.getOrder());
    }
}
