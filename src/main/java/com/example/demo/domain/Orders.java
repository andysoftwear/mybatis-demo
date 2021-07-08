package com.example.demo.domain;

import java.io.Serializable;

public class Orders implements Serializable {

    private Long id;
    private Long userno;
    private Integer num;
    private String note;
    private String ctime;
    private User user;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
