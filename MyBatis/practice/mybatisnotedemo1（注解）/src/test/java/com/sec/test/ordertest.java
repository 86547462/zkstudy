package com.sec.test;

import com.sec.Mapper.OrderDao;
import com.sec.Mapper.userdao;
import com.sec.model.OrderInfo;
import com.sec.model.UserInfo;
import com.sun.org.apache.xml.internal.resolver.readers.OASISXMLCatalogReader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ordertest {
    private OrderDao dao;
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
        dao= sqlSession.getMapper(OrderDao.class);
    }
    /**
     * 查询全部
     */
    @Test
    public  void test1() throws IOException {
        List<OrderInfo> list=dao.selAll();
        for (OrderInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }

    /**
     * 查询单个
     * @throws IOException
     */
    @Test
    public  void test2() throws IOException {
        List<OrderInfo> list=dao.selIds(2);
        for (OrderInfo info:list
        ) {
            System.out.println(info);
        }
        sqlSession.close();
    }


}
