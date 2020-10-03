package com.demo2;

import java.util.concurrent.*;

/**
 *创建线程方法3， 实现callable接口
 * 可以定义返回值
 */
public class TestCallable implements Callable<Integer> {
    public Integer call() throws Exception {

        for(int i=0;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+"====>"+i);
        }
        return 100;
    }
    public static void main(String[] args)
    {
        TestCallable t1=new TestCallable();
        TestCallable t2=new TestCallable();
        TestCallable t3=new TestCallable();


        //创建执行服务
        ExecutorService ser= Executors.newFixedThreadPool(3);

        //提交执行
        Future<Integer> f1=ser.submit(t1);
        Future<Integer> f2=ser.submit(t2);
        Future<Integer> f3=ser.submit(t3);

        //获取结果
        try {
            int i1=f1.get();
            int i2=f2.get();
            int i3=f3.get();
            System.out.println("结果："+i1+i2+i3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //关闭服务
        ser.shutdown();
    }

}
