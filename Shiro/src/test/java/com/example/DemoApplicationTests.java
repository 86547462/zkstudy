package com.example;

import com.example.Service.ServiceImpl.UserServerImpl;
import com.example.Service.UserService;
import com.example.pojo.userInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserServerImpl server;
    @Test
    void contextLoads() {
        userInfo list=server.userPwd("admin");
       // list.forEach(System.out::println);
        System.out.println(list);

    }

}
