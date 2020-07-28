package com.kun;

/**
 * 创建超线方法2，实现Runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用statr方法
 * 推荐使用runnable接口，比秒了单继承的局限性，灵活方便，方便同一个对象被多个线程使用
 */
public class TestThread2 implements Runnable {
    public void run() {
        //run方法体线程
        for (int i=0;i<10;i++){
            System.out.println("我在吃饭");
        }
    }
    public static void main(String[] args) {
        //mian线程，主线程
        TestThread2 testThread2=new TestThread2();
        new Thread(testThread2).start();

        for (int i=0;i<10;i++){
            System.out.println("我在睡觉zzzzzzzz");
        }

    }
}
