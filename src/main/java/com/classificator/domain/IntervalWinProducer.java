package com.classificator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "interval_win_producer", schema = "movies")
public class IntervalWinProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_producer")
    private Long idProducer;

    @Column(name = "name")
    private String name;

    @Column(name = "interval_win")
    private Long intervalWin;

    @Column(name = "previous_win")
    private Long previousWin;

    @Column(name = "following_win")
    private Long followingWin;
    
    
    public IntervalWinProducer() {
    }

    public IntervalWinProducer(Long idProducer, String name, Long intervalWin, Long previousWin, Long followingWin) {
        this.idProducer = idProducer;
        this.name = name;
        this.intervalWin = intervalWin;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIntervalWin() {
        return intervalWin;
    }

    public void setIntervalWin(Long intervalWin) {
        this.intervalWin = intervalWin;
    }

    public Long getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Long previousWin) {
        this.previousWin = previousWin;
    }

    public Long getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Long followingWin) {
        this.followingWin = followingWin;
    }

}
