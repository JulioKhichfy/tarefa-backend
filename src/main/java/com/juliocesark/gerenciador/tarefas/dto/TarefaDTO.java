package com.juliocesark.gerenciador.tarefas.dto;
import java.time.LocalDate;

public class TarefaDTO {

    private Long id;
    private String name;
    private String price;
    private LocalDate limitDate;

    public TarefaDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}
