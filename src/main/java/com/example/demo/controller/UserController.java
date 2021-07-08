package com.example.demo.controller;

import com.example.demo.server.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserServer userServer;

    @PostMapping("/all")
    public Object getAllUsers(HttpServletRequest request, HttpServletResponse response){
        return userServer.queryAllUsers();
    }

    @GetMapping("/one")
    public Object getOneUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("userno") Long userno){
        return userServer.queryOneUser(userno);
    }

    @GetMapping("/order")
    public Object getUserOrder(HttpServletRequest request, HttpServletResponse response){
        return userServer.findUserOrders();
    }
}
