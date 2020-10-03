package com.sec.demo1;

import java.lang.annotation.ElementType;
import java.util.concurrent.Callable;

/**
 * 所有类型的class
 */
public class Test03 {


    public static void main(String[] args) {
        Class c1=Object.class;//类
        Class c2=Callable.class;//接口
        Class c3=String.class;
        Class c4=StringBuffer.class;//基本数据类型
        Class c5=int[].class;//一维数组
        Class c6=int[][].class;//二维数组
        Class c7=Override.class;//注解
        Class c8= ElementType.class;//枚举
        Class c9=Integer.class;//基本数据类型
        Class c10=void.class;//void
        Class c11=Class.class;//Class

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println(c10);
        System.out.println(c11);


        /**
         * 只要元素类型，与维度一样，就是同一个class
         */
        int [] a=new int[10];
        int [] b=new int[100];


        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());

    }
}
