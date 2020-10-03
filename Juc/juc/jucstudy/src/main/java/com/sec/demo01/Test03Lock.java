package com.sec.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁
 */
public class Test03Lock {
    public static void main(String[] args) {
        Ticket03 ticket=new Ticket03();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                ticket.sale();
            },+i+"").start();
        }


    }
}


/**
 * 资源类，OOP
 */
class Ticket03{
    private Integer number=20;

    Lock lock=new ReentrantLock();


    //买票的方法
    public  void sale()
    {
        //枷锁
        lock.lock();
        try {
            if(number>0)
            {

                System.out.println(Thread.currentThread().getName()+"买到了第"+number--+"张票，剩余"+number+"张票。。。");
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            lock.unlock();//解锁
        }


    }

}