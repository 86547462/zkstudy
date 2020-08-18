package com.sec.test;


import com.sec.Mapper.OrderDao;
import com.sec.Mapper.userdao;
import com.sec.model.OrderInfo;
import com.sec.model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class usertest {

    /**
     * 一对一（一个订单有一个用户）
     * @throws IOException
     */
    @Test
    public  void test1() throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        OrderDao dao=sqlSession.getMapper(OrderDao.class);
        List<OrderInfo> list=dao.selAll();
        for (OrderInfo info:list
             ) {
            System.out.println(info);
        }
        sqlSession.close();

    }

    /**
     * 多对一（一个用户有多个订单）
     */
    @Test
    public  void test2() throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        userdao dao=sqlSession.getMapper(userdao .class);
        List<UserInfo> list=dao.findAll();
        for (UserInfo info:list
        ) {
            System.out.println(info);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        sqlSession.close();

    }

    /**
     * 多对多
     * @throws IOException
     */
    @Test
    public  void test3() throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        userdao dao=sqlSession.getMapper(userdao .class);
        UserInfo info=new UserInfo();
        info.setUser_id(2);
        List<UserInfo> list=dao.findRoleAll(info);
        for (UserInfo inf:list
        ) {
            System.out.println(inf);
        }
        sqlSession.close();

    }

}
