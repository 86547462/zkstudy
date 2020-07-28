package com.sec;

import com.sec.config.RedisConfig;
import com.sec.pojo.user;
import com.sec.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;


    @Test
    private void contextLoads() {

        //操作不同数据类型，操作和指令一样
//        redisTemplate.opsForValue();//操作字符串类型
//        redisTemplate.opsForHash();
//        redisTemplate.opsForList();
//        redisTemplate.opsForSet();
//        redisTemplate.opsForZSet();
//        redisTemplate.opsForGeo();
//        redisTemplate.opsForHyperLogLog();

        //获取redis连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushAll();
//        connection.flushDb();
//
        redisTemplate.opsForValue().set("name","周坤");
        System.out.println(redisTemplate.opsForValue().get("name"));


    }

    @Test
    public  void test()
    {
        user user=new user("张三","男",3);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));

    }



}
