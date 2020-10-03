package com.kun;

/**
 * 线程优先级
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程的优先级是=======》"+Thread.currentThread().getPriority());

        MyPriority myPriority=new MyPriority();
        Thread t1=new Thread(myPriority,"线程1");
        Thread t2=new Thread(myPriority,"线程2");
        Thread t3=new Thread(myPriority,"线程3");
        Thread t4=new Thread(myPriority,"线程4");
        Thread t5=new Thread(myPriority,"线程5");
        //设置线程优先级，（1-10），默认线程优先级5
        t1.setPriority(1);
        t2.setPriority(3);
        t3.setPriority(5);
        t4.setPriority(7);
        t5.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}



class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程的优先级是=======》"+Thread.currentThread().getPriority());
    }
}