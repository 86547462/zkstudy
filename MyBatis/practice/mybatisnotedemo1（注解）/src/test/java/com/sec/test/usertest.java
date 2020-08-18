package com.sec.test;


import com.sec.Mapper.OrderDao;
import com.sec.Mapper.userdao;
import com.sec.model.OrderInfo;
import com.sec.model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class usertest {
    private userdao dao;
    SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        sqlSession=sqlSessionFactory.openSession(true);
        //执行操作，
       dao= sqlSession.getMapper(userdao.class);
    }


    /**
     * 查询全部
     */
    @Test
    public  void test1() throws IOException {
        List<UserInfo> list=dao.findAll();
        for (UserInfo info:list
             ) {
            System.out.println(info);
        }
        sqlSession.close();
    }

    /**
     * 查询单个
     */
    @Test
    public  void test2() throws IOException {
        List<UserInfo> list=dao.findByIds(2);
        for (UserInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }


    /**
     * 修改
     */
    @Test
    public  void test3() throws IOException {
        UserInfo info=new UserInfo();
        info.setUser_name("小刚");
        info.setUser_age(14);
        info.setUser_id(8);

        dao.updateUser(info);

        sqlSession.close();
    }
    /**
     * 删除
     */
    @Test
    public  void test4() throws IOException {

        dao.deleteUser(11);

        sqlSession.close();
    }

    /**
     * 一对多查询
     */
    @Test
    public  void test5() throws IOException {



        List<UserInfo> list=dao.selUserOrderAll();
        for (UserInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }

    /**
     * 多对多查询
     */
    @Test
    public  void test6() throws IOException {



        List<UserInfo> list=dao.selUserRole();
        for (UserInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }

    /**
     * 多对多查询
     */
    @Test
    public  void test7() throws IOException {



        List<UserInfo> list=dao.selUserRole2();
        for (UserInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }
}
