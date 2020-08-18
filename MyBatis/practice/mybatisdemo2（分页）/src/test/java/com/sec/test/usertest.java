package com.sec.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sec.Mapper.userdao;
import com.sec.model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class usertest {
    /**
     * 查询多条件的数据
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
        userdao dao=sqlSession.getMapper(userdao.class);
        //模拟条件
        UserInfo info=new UserInfo();
       info.setUser_id(2);
      // info.setUser_name("砸坏");
       // info.setUser_age(15);
        List<UserInfo> list=dao.findByCondition(info);
        System.out.println(list);
        sqlSession.close();
    }

    /**
     * 查询多条数据
     * @throws IOException
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
        userdao dao=sqlSession.getMapper(userdao.class);
        //模拟条件
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<UserInfo> list1=dao.findByIds(list);
        System.out.println(list1);
        sqlSession.close();
    }

    /***
     * 批量删除
     */

    @Test
    public  void test7() throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        //执行操作，
        userdao dao=sqlSession.getMapper(userdao.class);
        //模拟条件
        List<Integer> list=new ArrayList<>();
        list.add(19);
        list.add(2);

        dao.deltetAll(list);
        sqlSession.close();
    }


    /**
     * 分页插件使用
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
        userdao dao=sqlSession.getMapper(userdao.class);

        //查询前设置分页参数
        PageHelper.startPage(1,3);
        List<UserInfo> list1=dao.findAll();
        for (UserInfo info:list1) {
            System.out.println(info);
        }

        //获得分页相关的参数
        PageInfo<UserInfo> pageInfo=new PageInfo<>(list1);
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页显示条数："+pageInfo.getPageSize());
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否是第最后一页："+pageInfo.isIsLastPage());


    }

}
