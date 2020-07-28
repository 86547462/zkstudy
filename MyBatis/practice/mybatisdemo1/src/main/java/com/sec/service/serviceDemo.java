package com.sec.service;

import com.sec.Mapper.userdao;
import com.sec.model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class serviceDemo {
    public static void main(String[] args) throws IOException {
        //获取核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        userdao dao=sqlSession.getMapper(userdao.class);
        List<UserInfo> list=dao.findAll();
        System.out.println(list);
    }
}
