package com.sec.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 性能检测
 */
public class Test06 {

    //普通方式调用
    public static void test01()
    {
        User user=new User();
        //开始时间
        Long start=System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {

            user.getName();
        }
        //结束时间
        Long end=System.currentTimeMillis();
        System.out.println("普通方法执行10一次用时："+(end-start)+"ms");
    }
    //反射方式调用
    public static void test02() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.sec.demo1.User");
        User user=new User();

        Method getName = aClass.getMethod("getName");
        //开始时间
        Long start=System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {

            getName.invoke(user,null);
            
        }
        //结束时间
        Long end=System.currentTimeMillis();
        System.out.println("反射方式调用10一次用时："+(end-start)+"ms");
    }
    //反射方式调用（关闭检测）
    public static void test03() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.sec.demo1.User");

        User user=new User();
        Method getName = aClass.getMethod("getName");
        getName.setAccessible(true);
        //开始时间
        Long start=System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {

            getName.invoke(user,null);

        }
        //结束时间
        Long end=System.currentTimeMillis();
        System.out.println("反射方式调用（关闭检测）用时："+(end-start)+"ms");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        test01();
        test02();
        test03();
    }
}
