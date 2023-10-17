package com.juliocesark.gerenciador.tarefas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_tarefa")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", unique=true, nullable=false)
    private String name;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="limitDate", nullable=false)
    private Date limitDate;

    @Column(name="order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order;

    public Task(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && name.equals(task.name) && price.equals(task.price) && limitDate.equals(task.limitDate) && order.equals(task.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, limitDate, order);
    }
}
