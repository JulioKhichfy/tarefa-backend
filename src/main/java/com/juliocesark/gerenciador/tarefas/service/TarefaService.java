package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.converter.DozerConverter;
import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TarefaService {

    private Logger logger = Logger.getLogger(TarefaService.class.getName());

    @Autowired
    private TarefaRepository repository;

    public TarefaDTO find(Long id) {
        logger.info("Executando o método find com id = " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Não foi encontrada tarefa com esse Id: " + id));
        return DozerConverter.parseObject(entity, TarefaDTO.class);
    }

    public TarefaDTO create(TarefaDTO tarefaDTO) {
        logger.info("Executando o método create");
        if(tarefaDTO == null) throw new TaskNullableException("Error: Tarefa é null");

        checkName(tarefaDTO.getName().trim());
        checkPrice(tarefaDTO.getPrice().trim());
        checkDate(tarefaDTO.getLimitDate());

        var entity = DozerConverter.parseObject(tarefaDTO, Tarefa.class);
        entity.setOrder(getOrder());
        entity.setName(entity.getName().trim());
        var dto = DozerConverter.parseObject(repository.save(entity), TarefaDTO.class);
        return dto;
    }

    public TarefaDTO update(TarefaDTO tarefaDTO) {
        logger.info("Executando o método update");
        if(tarefaDTO == null) throw new TaskNullableException("Error: Tarefa é null");

        checkUpdateName(tarefaDTO);
        checkPrice(tarefaDTO.getPrice().trim());
        checkDate(tarefaDTO.getLimitDate());

        var entity = repository.findById(tarefaDTO.getId())
                .orElseThrow(() -> new TaskNotFoundException("Não foi encontrada tarefa com esse Id: " + tarefaDTO.getId()));

        entity.setName(tarefaDTO.getName().trim());
        entity.setPrice(new BigDecimal(tarefaDTO.getPrice()));
        entity.setLimitDate(tarefaDTO.getLimitDate());

        var dto = DozerConverter.parseObject(repository.save(entity), TarefaDTO.class);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Executando o método delete para o id = " + id);

        Tarefa entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Não foi encontrada tarefa com esse Id: " + id));
        repository.delete(entity);
    }

    public List<TarefaDTO> findAll() {
        logger.info("Executando o método findAll");

        List<Tarefa> listOrdenada = repository.findAll().stream()
                .sorted(Comparator.comparingLong(Tarefa::getOrder).reversed())
                .toList();
        return DozerConverter.parseListObjects(listOrdenada, TarefaDTO.class);
    }

    private Long getOrder() {
        Long count = repository.count();
        if (count == null) return 1L;
        return count + 1;
    }

    public void updateOrder(Long id1, Long id2) {
        logger.info("Executando o método updateOrder id1 = " + id1 + " id2 = " + id2);

        Optional<Tarefa> tarefa1 = repository.findById(id1);
        Optional<Tarefa> tarefa2 = repository.findById(id2);

        Long novaOrdem1 = tarefa2.get().getOrder();
        Long novaOrdem2 = tarefa1.get().getOrder();

        tarefa1.get().setOrder(novaOrdem1);
        tarefa2.get().setOrder(novaOrdem2);

        repository.save(tarefa1.get());
        repository.save(tarefa2.get());
    }

    private void checkDate(LocalDate date) {
        if(date == null)
            throw new TaskInvalidDateException("A data deve ser informada!");

        LocalDate today = LocalDate.now();

        if (date.isBefore(today))
            throw new TaskInvalidDateException("A Data informada não pode ser anterior a data corrente!");
    }

    private void checkName(String name) {
        if(name == null || "".equals(name))
            throw new TaskNameException("O nome da tarefa deve ser informado!");

        Tarefa hasEntity = repository.findByNameIgnoreCase(name);
        if(hasEntity != null)
            throw new TaskNameException("O nome da tarefa ja existe, por favor informe outro nome!");
    }

    private void checkUpdateName(TarefaDTO tarefaDTO){
        if(tarefaDTO.getName() == null || "".equals(tarefaDTO.getName().trim()))
            throw new TaskNameException("O nome da tarefa deve ser informado!");

        Tarefa hasEntity = repository.findByNameIgnoreCase(tarefaDTO.getName().trim());
        if(hasEntity != null && hasEntity.getId() != tarefaDTO.getId())
            throw new TaskNameException("O nome da tarefa ja existe, por favor informe outro nome.");
    }

    private void checkPrice(String price) {
        if (price == null || "".equals(price))
            throw new TaskPriceException("O preço deve ser informado!");
        BigDecimal p = BigDecimal.ZERO;
        try {
            p = new BigDecimal(price);
        }catch (NumberFormatException e){
            throw new TaskPriceException("O preço deve ser númerico!");
        }
        if(p.intValue() == 0)
            throw new TaskPriceException("O preço deve ser maior que zero!");
    }
}