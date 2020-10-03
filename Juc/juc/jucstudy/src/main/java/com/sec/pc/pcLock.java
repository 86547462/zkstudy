package com.sec.pc;

import sun.plugin.security.StripClassFile;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class pcLock {
    public static void main(String[] args) {
        Data2 data=new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                    data.increment();
                  }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"D").start();
    }
}
//判断业务通知

class Data2{
    private int number=0;
    Lock lock=new ReentrantLock();
    Condition condition = lock.newCondition();
    //condition.await();//等待
    //condition.signalAll();//唤醒全部

    //加一操作
    public void increment(){

        //加锁
        lock.lock();
        try {
            //业务代码
            while (number!=0)
            {
                condition.await();//等待
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"====>"+number);
           condition.signalAll();//唤醒全部
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }

    //减一操作
    public void decrement(){
        //加锁
        lock.lock();
        try {
            //业务代码
            while (number!=1) {
                condition.await();//等待
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "====>" + number);
            condition.signalAll();//唤醒全部
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}