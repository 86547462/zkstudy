package com.kun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池
 */
public class TestPool implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        //创建线程池，创建服务
        ExecutorService ser= Executors.newFixedThreadPool(10);//线程池数量

        //执行
        ser.execute(new TestPool());
        ser.execute(new TestPool());
        ser.execute(new TestPool());
        ser.execute(new TestPool());
        ser.execute(new TestPool());
        ser.execute(new TestPool());
        //executorService.submit();//适合使用于Callable
        //关闭线程池
        ser.shutdown();
    }

}
