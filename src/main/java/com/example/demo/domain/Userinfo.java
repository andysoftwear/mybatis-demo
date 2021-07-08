package com.example.demo.domain;

public class Userinfo {

    private Long id;
    private Long userno;
    private String cours;
    private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserno() {
        return userno;
    }

    public void setUserno(Long userno) {
        this.userno = userno;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
