package com.sec.pc;

public class pcAsyn {
    public static void main(String[] args) {
        Data data=new Data();

            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }     }
            },"A").start();


            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
            },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
        },"D").start();
    }
}

//判断业务通知

class Data{
    private int number=0;



    //加一操作
    public synchronized void increment() throws InterruptedException {
        while (number!=0)
        {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"====>"+number);
        this.notifyAll();

    }

    //减一操作
    public synchronized void decrement() throws InterruptedException {
        /**
         * if判断会引起虚假唤醒
         */
        while (number==0)
        {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"====>"+number);
        this.notifyAll();
    }


}