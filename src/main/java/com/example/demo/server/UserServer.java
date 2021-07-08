package com.example.demo.server;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.domain.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServer {

    @Autowired
    private UserDao userDao;

    public List<User> queryAllUsers(){
        return userDao.queryAllUsers();
    }

    public List<Userinfo> queryOneUser(Long userno){
        return userDao.queryOneUser(userno);
    }
}
