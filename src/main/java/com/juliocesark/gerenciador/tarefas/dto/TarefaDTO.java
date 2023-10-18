package com.juliocesark.gerenciador.tarefas.dto;


import com.juliocesark.gerenciador.tarefas.model.Tarefa;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TarefaDTO {

    private String name;
    private String price;
    private String limitDate;

    public TarefaDTO(){

    }

    public TarefaDTO(Tarefa tarefa){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.name = tarefa.getName();
        this.price = tarefa.getPrice().toString();
        this.limitDate = formatter.format(tarefa.getLimitDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }
}
