package com.sec.demo01;

/**
 * 传统的synchronized
 */
public class Test02 {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
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
class Ticket{
    private Integer number=20;

    //买票的方法
    public synchronized void sale()
    {
        if(number>0)
        {

            System.out.println(Thread.currentThread().getName()+"买到了第"+number--+"张票，剩余"+number+"张票。。。");
        }
    }

}