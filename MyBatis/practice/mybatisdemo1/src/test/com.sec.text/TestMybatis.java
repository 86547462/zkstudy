package com.sec.text;

import com.sec.model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class TestMybatis {

    /**
     * 查询操作
     * @throws IOException
     */
        @Test
        public void test1() throws IOException {
            //获取核心配置文件
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //获取session会话对象
            SqlSession sqlSession=sqlSessionFactory.openSession();
            //执行操作，
//            List<UserInfo> list=sqlSession.selectList("userMapper.findAll");
            List<UserInfo> list1=sqlSession.selectOne("userMapper.findId",1);
            System.out.println(list1);
            sqlSession.close();
        }


    /**
     * 插入操作
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        //获取核心配置文件
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        UserInfo info=new UserInfo();
        info.setUser_age(45);
        info.setUser_name("小ma");
            sqlSession.insert("userMapper.saveUser",info);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     */

    @Test
    public void test3() throws IOException {
        //获取核心配置文件
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行操作，
        UserInfo info=new UserInfo();
        info.setUser_id(10);
        info.setUser_age(18);
        info.setUser_name("小美");
        sqlSession.update("userMapper.updateUser",info);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 删除
     */

    @Test
    public void test4() throws IOException {
        //获取核心配置文件
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config.xml");
        //获取session工厂对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取session会话对象
        //加true自动提交
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        //执行操作，
        UserInfo info=new UserInfo();
        info.setUser_id(5);

        sqlSession.delete("userMapper.delUser",1);
        sqlSession.close();
    }
}
