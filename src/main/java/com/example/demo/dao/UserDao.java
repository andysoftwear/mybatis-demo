package com.example.demo.dao;

import com.example.demo.domain.Orders;
import com.example.demo.domain.User;
import com.example.demo.domain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> queryAllUsers();
    List<Userinfo> queryOneUser(@Param("userno") Long userno);
    List<Orders> findUserOrders();
}
