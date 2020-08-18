package com.sec;

import com.sec.pojo.user;
import com.sec.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    //注入redis工具类
    @Autowired
    RedisUtils redisUtils;
    @Test
    public void test1(){
        redisUtils.set("user1",new user("李四","女",13));
        System.out.println(redisUtils.get("user1"));
    }
}
