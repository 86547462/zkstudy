package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.userMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    userMapper mapper;

    //查询全部
    @Test
    void contextLoads() {
        List<User> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

    //查询name，邮箱不为空，年龄大于12岁的用户
    @Test
    void conTestWapper1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        List<User> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //查询name是李四的用户（单个）
    @Test
    void conTestWapper2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "李四");
        User user = mapper.selectOne(wrapper);//查询一个数据

        System.out.println(user);
    }

    //查询10~~20之间的用户
    @Test
    void conTestWapper3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 10, 20);
        List list = mapper.selectList(wrapper);

        int count = mapper.selectCount(wrapper);
        list.forEach(System.out::println);
        System.out.printf("查出来的个数：" + count);
    }

    //模糊查询
    @Test
    void conTestWapper4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "8654");
        List<Map<String, Object>> list = mapper.selectMaps(wrapper);

        list.forEach(System.out::println);
    }

    //id在子查询中查询出来
    @Test
    void conTestWapper5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select version from user where id=1");
        List<Map<String, Object>> list = mapper.selectMaps(wrapper);

        list.forEach(System.out::println);
    }

    //排序
    @Test
    void conTestWapper6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");//降序  orderByAsc升序
        List<Map<String, Object>> list = mapper.selectMaps(wrapper);
        list.forEach(System.out::println);
    }
}
