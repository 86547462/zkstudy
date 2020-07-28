package com.kun;

//创建多线程方法一：继承Thread类，重写run方法，调用start开启线程
//注意：线程开启不一定立即执行，由cpu调度
public class TestThread1 extends Thread {
    @Override
    public void run() {
        //run方法体线程
        for (int i=0;i<10;i++){
            System.out.println("我在吃饭");
        }
    }

    //mian线程，主线程
    public static void main(String[] args) {
        //TestThread1线程
        TestThread1 testThread1=new TestThread1();
        testThread1.start();//启动线程
        for (int i=0;i<10;i++){
            System.out.println("我在睡觉zzzzzzzz");
        }

    }

}
