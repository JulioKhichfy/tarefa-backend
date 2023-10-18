package com.juliocesark.gerenciador.tarefas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique=true, nullable=false)
    private String name;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="limitDate", nullable=false)
    private Date limitDate;

    @Column(name = "order")
    private Long order;


    public Tarefa(){}


    public Tarefa(String name, String price, Date limitDate) {
        super();
        this.name = name;
        this.price = new BigDecimal(price);
        this.limitDate = limitDate;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id.equals(tarefa.id) && name.equals(tarefa.name) && price.equals(tarefa.price) && limitDate.equals(tarefa.limitDate) && order.equals(tarefa.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, limitDate, order);
    }
}
