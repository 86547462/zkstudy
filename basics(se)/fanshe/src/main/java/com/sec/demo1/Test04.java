package com.sec.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取类的信息
 */
public class Test04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.sec.demo1.User");

        //获取类的名字
        System.out.println(c1.getName());//获取包名+类名
        System.out.println(c1.getSimpleName());//类名

        //获取类的属性
        Field[] fields = c1.getFields();//只能获取public属性
        Field[] fieldall = c1.getDeclaredFields();//获取全部属性
        for (Field field : fields) {
            System.out.println(field);
        }

        //获取指定属性
        Field fieldname = c1.getDeclaredField("name");
        System.out.println(fieldname);

        System.out.println("============================================");

        //获取类的方法

         Method[] methodsall = c1.getMethods();//获取本类以及父类的所有方法

        for (Method method : methodsall) {
            System.out.println("全部"+method);
        }

        Method[] method = c1.getDeclaredMethods();//获取本类的所有方法
        for (Method method1 : method) {
            System.out.println(method1);
        }
        Method getname= c1.getMethod("getName",null);//获取指定方法
        Method setname= c1.getMethod("setName", String.class);//获取指定方法
        System.out.println(getname);
        System.out.println(setname);

        System.out.println("============================================");
        //获取构造器
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor[] declaredConstructors = c1.getDeclaredConstructors();

        for (Constructor declaredConstructor : declaredConstructors) {

            System.out.println(declaredConstructor);
        }

        //获取指定构造器
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, Integer.class, String.class);
        System.out.println("指定构造器"+declaredConstructor);

    }
}
