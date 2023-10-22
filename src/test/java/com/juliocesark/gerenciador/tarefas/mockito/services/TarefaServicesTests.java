package com.juliocesark.gerenciador.tarefas.mockito.services;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.mapper.mock.MockTarefa;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.TarefaService;
import com.juliocesark.gerenciador.tarefas.service.exceptions.TaskNullableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.find(1L);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getName());
        Assertions.assertNotNull(result.getPrice());
        Assertions.assertNotNull(result.getLimitDate());
        assertEquals("tarefa1", result.getName().toLowerCase());
    }

    @Test
    void testCreateWithNullTask() {
        Exception exception = assertThrows(TaskNullableException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "Error: Tarefa é null";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Tarefa entity = input.mockEntity(1);
        Tarefa persisted = entity;
        persisted.setId(1L);
        TarefaDTO dto = input.mockDTO(1);
        dto.setName("OUTRA TAREFA");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(dto);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("OUTRA TAREFA", result.getName());
    }

    @Test
    void testDelete() {
        Tarefa entity = input.mockEntity(1);
        entity.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }

    @Test
    void testFindAll() {
        List<Tarefa> list = input.mockEntityList();
        Mockito.when(repository.findAll()).thenReturn(list);
        var tarefas = service.findAll();
        Assertions.assertNotNull(tarefas);
        assertEquals(14, tarefas.size());
        var tarefa1 = tarefas.get(1);

        Assertions.assertNotNull(tarefa1);
        Assertions.assertNotNull(tarefa1.getId());
        Assertions.assertNotNull(tarefa1.getName());
        Assertions.assertNotNull(tarefa1.getPrice());
        Assertions.assertNotNull(tarefa1.getLimitDate());
    }
}
