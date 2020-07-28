package com.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.userMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    userMapper mapper;

    //查询全部
    @Test
    void contextLoads() {
        List<User> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

    //插入数据
    @Test
    void TestInsert() {
//        if(mapper.insert(new User(12,"张三",3,"86547462@qq.com"))>0)
//        {
//            System.out.println("插入成功");
//        }
        User user = new User();
        user.setName("王八");
        user.setAge(5);
        user.setEmail("86547462@qq.com");
        int i = mapper.insert(user);
        System.out.printf("受影响的行数" + i);//收影响的行数
        System.out.println(user);//发现id自动会填

    }

    //更新操作
    @Test
    void TestUpate() {

        User user = new User();
        user.setId(1294497356014632961l);
        user.setName("杰尼");
        user.setAge(5);
        user.setEmail("86547462@qq.com");
        //虽然参数数updateById，但里面放的是一个对象
        int i = mapper.updateById(user);
        System.out.printf("" + i);

    }

    //乐观锁测试

    @Test
    void TestVersion() {
        User user = mapper.selectById(1);
        user.setName("托尼");
        mapper.updateById(user);


    }

    //乐观锁模拟多线程失败
    @Test
    void TestVersion1() {
        //线程1
        User user1 = mapper.selectById(2);
        user1.setName("杰克1");
        //线程2
        User user2 = mapper.selectById(2);
        user2.setName("杰克2");
        //线程2先执行了修改
        mapper.updateById(user2);
        //线程1后执行了修改
        mapper.updateById(user1);

    }
    //查询

    //多条查询
    @Test
    void TestSelectIds() {
        List list = mapper.selectBatchIds(Arrays.asList(0, 1, 2, 3));
        list.forEach(System.out::println);
    }

    //多条件查询
    @Test
    void TestSelectBybachIds() {
        Map map = new HashMap();
        map.put("name", "杰尼");
        map.put("age", 5);
        List list = mapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    //分页查询
    @Test
    void TestSelectPage() {
        Page<User> page = new Page<>(1, 5);
        mapper.selectPage(page, null);

        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println(page.getTotal());//总页数
    }

    //删除

    //单个删除
    @Test
    void deleteid() {
        mapper.deleteById(1L);
    }

    //批量删除
    @Test
    void deleteidArrays() {
        mapper.deleteBatchIds(Arrays.asList(3, 4, 5));
    }

    //条件删除
    @Test
    void deleteidMapper() {
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("age", 3);
        mapper.deleteByMap(map);
    }


}
