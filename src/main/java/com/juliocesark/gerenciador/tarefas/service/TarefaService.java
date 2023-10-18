package com.juliocesark.gerenciador.tarefas.service;

import com.juliocesark.gerenciador.tarefas.dto.TarefaDTO;
import com.juliocesark.gerenciador.tarefas.model.Tarefa;
import com.juliocesark.gerenciador.tarefas.repositories.TarefaRepository;
import com.juliocesark.gerenciador.tarefas.service.exceptions.DateFormatterException;
import com.juliocesark.gerenciador.tarefas.service.exceptions.InvalidDateException;
import com.juliocesark.gerenciador.tarefas.service.exceptions.ObjectNotFoundException;
import com.juliocesark.gerenciador.tarefas.service.exceptions.TaskNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
        return tarefa.get(); // 200 OK
    }

    public Tarefa save(Tarefa tarefa) {
        logger.info("Executando o método save");
        checkDate(tarefa);
        tarefa.setOrder(getOrder());
        tarefa.setName(tarefa.getName().toUpperCase());
        try {
            return repository.save(tarefa);
        } catch(DataIntegrityViolationException e) {
            throw new TaskNameException("Verifique se o nome da tarefa já existe: " + tarefa.getName());
        }
    }

    public Tarefa update(Tarefa tarefa) {
        logger.info("Executando o método update");
        checkDate(tarefa);
        Tarefa newObj = find(tarefa.getId());
        tarefa.setName(tarefa.getName().toUpperCase());
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
        if(count == null) return 1L;
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
        String dateInString = tarefaDto.getLimitDate();
        Date date;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e ){
            throw new DateFormatterException("A data informada precisa estar no padrão dd/mm/yyyy! date: " + dateInString);
        }
        return new Tarefa(tarefaDto.getName().toUpperCase(), tarefaDto.getPrice(), date);
    }

    private void checkDate(Tarefa tarefa){
        Date today = new Date();
        if(tarefa.getLimitDate().before(today)){
            String dataStr = formatter.format(tarefa.getLimitDate());
            throw new InvalidDateException("A Data informada é anterior a data corrente! " + dataStr);
        }
    }
}