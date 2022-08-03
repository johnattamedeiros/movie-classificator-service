package com.classificator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "producer_winner", schema = "movies")
public class ProducerWinner {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "year_date")
    private Long yearDate;

    @Column(name = "id_producer")
    private Long idProducer;

    @Column(name = "name")
    private String name;

    public ProducerWinner() {

    }

    public ProducerWinner(Long idProducer, String name) {
        this.idProducer = idProducer;
        this.name = name;
    }

    public Long getYearDate() {
        return yearDate;
    }

    public void setYearDate(Long yearDate) {
        this.yearDate = yearDate;
    }

    public Long getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(Long idProducer) {
        this.idProducer = idProducer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
