package com.sec.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A线程执行完，调用B，再调用C，再A
 */
public class pcLockPlus {
    public static void main(String[] args) {
        Data3 data3=new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data3.A();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data3.B();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data3.C();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"c").start();

    }
}

class Data3{
    private Integer number=1;//1 A,2B,3C
    private Lock lock=new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void A() throws InterruptedException {
        lock.lock();

        try {
            while (number!=1)
            {
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"====>AAA"+number);
            number=2;
            condition2.signal();//唤醒指定的人

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void B() throws InterruptedException {
        lock.lock();
        try {
            while (number!=2)
            {
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"====>AAA"+number);
            number=3;
            condition3.signal();//唤醒指定的人

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void C() throws InterruptedException {
        lock.lock();

        try {
            while (number!=3)
            {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"====>AAA"+number);
            number=1;
            condition1.signal();//唤醒指定的人

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}