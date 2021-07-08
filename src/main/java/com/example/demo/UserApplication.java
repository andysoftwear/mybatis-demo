package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value={"com.example.demo.dao"})
@SpringBootApplication(scanBasePackages = {"com.example.demo.domain","com.example.demo.server","com.example.demo.controller"})
public class UserApplication {
    // 启动类添加需要扫描的包
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
