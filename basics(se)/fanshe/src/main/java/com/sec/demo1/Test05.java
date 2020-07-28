package com.sec.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射动态创建对象
 */
public class Test05 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获取class对象
        Class c1=Class.forName("com.sec.demo1.User");

        //构造一个对象
        User u1 = (User) c1.newInstance();//本质调用了类的无参构造器，如果没有无参构造就报错
        System.out.println(u1);


        //通过构造器创建对象

        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, Integer.class, String.class);
        User u2= (User) declaredConstructor.newInstance("张三",18,"北京");

        System.out.println(u2);


        //通过反射调用普通方法


        //1.获取方法
        /**
         * invoke:激活的意思（对象，方法的值）
         */
        Method getName = c1.getMethod("getName", null);
        String name= (String) getName.invoke(u2, null);
        System.out.println(name);
        Method setName =c1.getMethod("setName", String.class);
        setName.invoke(u1,"周坤");
        System.out.println(u1);


        //通过反射操作属性

        Field name1 = c1.getDeclaredField("name");
        //不能直接操作私有属性，我们需要关闭程序的安全检测，属性或方法的setAccessible(true)
        name1.setAccessible(true);
        name1.set(u1,"王八");
        System.out.println(u1.getName());

    }
}
