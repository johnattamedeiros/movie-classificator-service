package com.classificator.service.dto;

public class ProducerWinnerComparision {

    private Long id;
    private String name;
    private Long intervalWin;
    private Long previousWin;
    private Long followingWin;

    public ProducerWinnerComparision() {

    }

    public ProducerWinnerComparision(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ProducerWinnerComparision [id=" + id + ", name=" + name + ", intervalWin=" + intervalWin
                + ", previousWin=" + previousWin + ", followingWin=" + followingWin + "]";
    }

}
