package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private Long userno;
    private String name;
    private int age;
    private int sex;
    private List<Userinfo> userinfo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public List<Userinfo> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(List<Userinfo> userinfo) {
        this.userinfo = userinfo;
    }
}
