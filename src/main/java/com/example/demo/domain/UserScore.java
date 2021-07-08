package com.example.demo.domain;

import java.io.Serializable;

public class UserScore implements Serializable {

    private Long id;
    private Long userid;
    private Long coursid;
    private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getCoursid() {
        return coursid;
    }

    public void setCoursid(Long coursid) {
        this.coursid = coursid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
