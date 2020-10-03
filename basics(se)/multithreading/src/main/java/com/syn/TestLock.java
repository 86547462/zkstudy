package com.syn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock锁
 */
public class TestLock implements Runnable{
    private int ticcket=20;
    //定义lock锁
    private final ReentrantLock lock=new ReentrantLock();
    public  void run() {

        lock.lock();//加锁
        try {

            while (true){
                if(ticcket<=0){
                    break;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"抢到了第"+ticcket--+"张票");

            }
        }finally {
            //解锁
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        TestLock testLock=new TestLock();
        new Thread(testLock,"小明").start();
        new Thread(testLock,"小花").start();
        new Thread(testLock,"黄牛").start();
    }


}
