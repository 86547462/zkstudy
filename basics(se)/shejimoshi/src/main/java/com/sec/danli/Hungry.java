package com.sec.danli;

/**
 * 单例模式：确保一个类只有一个实例，并且自行实例化向整个系统提供这个实例
 *
 * 饿汉式单例模式
 */
public class Hungry {

    private Hungry(){

    }

    private static final  Hungry hungry=new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }

}
