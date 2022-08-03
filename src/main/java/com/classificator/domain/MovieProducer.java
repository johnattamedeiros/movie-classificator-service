package com.classificator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_producer", schema = "movies")
public class MovieProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_movie")
    private Long idMovie;

    @Column(name = "id_producer")
    private Long idProducer;

    @Column(name = "winner")
    private Boolean winner;
    
    public MovieProducer() {
        
    }

    public MovieProducer(Long idMovie, Long idProducer, Boolean winner) {
        this.idMovie = idMovie;
        this.idProducer = idProducer;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public Long getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(Long idProducer) {
        this.idProducer = idProducer;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

}
