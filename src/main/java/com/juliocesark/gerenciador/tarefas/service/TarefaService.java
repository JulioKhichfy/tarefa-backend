package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TarefaService {

    private Logger logger = Logger.getLogger(TarefaService.class.getName());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private TarefaRepository repository;

    public Tarefa find(Long id) {
        logger.info("Executando o método find com id = " + id);
        Optional<Tarefa> tarefa = repository.findById(id);
        if (!tarefa.isPresent())
            throw new ObjectNotFoundException("Tarefa não encontrada! Id: " + id);
        return tarefa.get(); // 200 OK
    }

    public Tarefa save(Tarefa tarefa) {
        logger.info("Executando o método save");
        if(tarefa == null) throw new TaskNameException("Tarefa nula");
        tarefa.setOrder(getOrder());
        return repository.save(tarefa);
    }

    public Tarefa update(Tarefa tarefa) {
        logger.info("Executando o método update");
        Tarefa newObj = find(tarefa.getId());
        updateData(newObj, tarefa);
        return repository.save(newObj);
    }

    public void delete(Long id) {
        logger.info("Executando o método delete para o id = " + id);
        find(id);
        repository.deleteById(id);
    }

    public List<Tarefa> findAll() {
        logger.info("Executando o método findAll");
        return repository.findAll();
    }

    private Long getOrder() {
        Long count = repository.count();
        if (count == null) return 1L;
        return count + 1;
    }

    private void updateData(Tarefa newObj, Tarefa obj) {
        newObj.setName(obj.getName());
        newObj.setPrice(obj.getPrice());
        newObj.setLimitDate(obj.getLimitDate());
    }

    public void updateOrder(Long id1, Long id2) {
        Tarefa tarefa1 = find(id1);
        Tarefa tarefa2 = find(id2);
        tarefa1.setId(id1);
        tarefa2.setId(id2);

        Long novaOrdem1 = tarefa2.getOrder();
        Long novaOrdem2 = tarefa1.getOrder();

        tarefa1.setOrder(novaOrdem1);
        tarefa2.setOrder(novaOrdem2);

        update(tarefa1);
        update(tarefa2);
    }

    public Tarefa fromDTO(TarefaDTO tarefaDto) {
        Date date = checkDate(tarefaDto);
        checkName(tarefaDto);
        checkPrice(tarefaDto);

        return new Tarefa(tarefaDto.getName().toUpperCase().trim(), tarefaDto.getPrice().trim(), date);
    }

    private Date checkDate(TarefaDTO dto) {
        Date today = new Date();
        Date date;
        if(dto.getLimitDate() == null || "".equals(dto.getLimitDate().trim()))
            throw new DateFormatterException("A data deve ser informada!");

        try {
            date = formatter.parse(dto.getLimitDate().trim());
        }catch(ParseException e){
            throw new DateFormatterException("A data informada deve estar no padrão dd/mm/yyyy!");
        }

        if (date.before(today)) {
            throw new InvalidDateException("A Data informada não pode ser anterior a data corrente!");
        }
        return date;
    }

    private void checkName(TarefaDTO dto) {
        if(dto.getName() == null || "".equals(dto.getName().trim()))
            throw new TaskNameException("O nome da tarefa não pode ser vazio");

        Tarefa tarefaModel = repository.findByName(dto.getName().toUpperCase().trim());
        if (tarefaModel != null)
            throw new TaskNameException("O nome da tarefa não pode ser repetido");
    }

    private void checkPrice(TarefaDTO dto) {
        if (dto.getPrice() == null || "".equals(dto.getPrice().trim()))
            throw new PriceException("O preço deve ser informado!");
        BigDecimal price = BigDecimal.ZERO;
        try {
            price = new BigDecimal(dto.getPrice().trim());
        }catch (NumberFormatException e){
            throw new PriceException("O preço deve ser númerico!");
        }
        if(price.intValue() == 0)
            throw new PriceException("O preço deve ser maior que zero!");
    }
}