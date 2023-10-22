package com.juliocesark.gerenciador.tarefas.mapper;

import com.juliocesark.gerenciador.tarefas.converter.DozerConverter;
import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.mapper.mock.MockTarefa;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DozerConverterTest {
    MockTarefa inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockTarefa();
    }

    @Test
    public void parseEntityToDTOTest() {
        TarefaDTO output = DozerConverter.parseObject(inputObject.mockEntity(), TarefaDTO.class);
        Assertions.assertEquals(Long.valueOf(0L), output.getId());
        Assertions.assertEquals("Tarefa0", output.getName());
        Assertions.assertEquals("0", output.getPrice());
        Assertions.assertEquals(LocalDate.now(), output.getLimitDate());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<TarefaDTO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), TarefaDTO.class);
        TarefaDTO outputZero = outputList.get(0);

        Assertions.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assertions.assertEquals("Tarefa0", outputZero.getName());
        Assertions.assertEquals("0", outputZero.getPrice());
        Assertions.assertEquals(LocalDate.now(), outputZero.getLimitDate());

        TarefaDTO outputSeven = outputList.get(7);

        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assertions.assertEquals("Tarefa7", outputSeven.getName());
        Assertions.assertEquals("7", outputSeven.getPrice());
        Assertions.assertEquals(LocalDate.now().plusDays(7), outputSeven.getLimitDate());

        TarefaDTO outputTwelve = outputList.get(12);

        Assertions.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assertions.assertEquals("Tarefa12", outputTwelve.getName());
        Assertions.assertEquals("12", outputTwelve.getPrice());
        Assertions.assertEquals(LocalDate.now().plusDays(12), outputTwelve.getLimitDate());
    }

    @Test
    public void parseDTOToEntityTest() {
        Tarefa output = DozerConverter.parseObject(inputObject.mockDTO(), Tarefa.class);
        Assertions.assertEquals(Long.valueOf(0L), output.getId());
        Assertions.assertEquals("Tarefa0", output.getName());
        Assertions.assertEquals(new BigDecimal("0"), output.getPrice());
        Assertions.assertEquals(LocalDate.now(), output.getLimitDate());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Tarefa> outputList = DozerConverter.parseListObjects(inputObject.mockDTOList(), Tarefa.class);
        Tarefa outputZero = outputList.get(0);

        Assertions.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assertions.assertEquals("Tarefa0", outputZero.getName());
        Assertions.assertEquals(new BigDecimal("0"), outputZero.getPrice());
        Assertions.assertEquals(LocalDate.now(), outputZero.getLimitDate());


        Tarefa outputSeven = outputList.get(7);

        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assertions.assertEquals("Tarefa7", outputSeven.getName());
        Assertions.assertEquals(new BigDecimal("7"), outputSeven.getPrice());
        Assertions.assertEquals(LocalDate.now().plusDays(7), outputSeven.getLimitDate());

        Tarefa outputTwelve = outputList.get(12);

        Assertions.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assertions.assertEquals("Tarefa12", outputTwelve.getName());
        Assertions.assertEquals(new BigDecimal("12"), outputTwelve.getPrice());
        Assertions.assertEquals(LocalDate.now().plusDays(12), outputTwelve.getLimitDate());
    }
}
