package com.sec.demo1;

/**
 * 反射
 */
public class Test01 {

    public static void main(String[] args) throws ClassNotFoundException {
        //通过反射获取类的class对象
        Class c1=Class.forName("com.sec.demo1.User");
        Class c2=Class.forName("com.sec.demo1.User");
        Class c3=Class.forName("com.sec.demo1.User");

        /**
         * 1.一个类在内存中只存在一个class对象
         * 2.一个类被加载后，类的整个结构都会封装在class对象中
         */
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
    }
}






