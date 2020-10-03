package com.sec.danli;
/**
 * 懒汉式单例模式
 */
public class lazyMain {

    private lazyMain(){
        System.out.println(Thread.currentThread().getName()+"单例模式");
    }


    private static  lazyMain lazymain;

    //双层检测锁模式，DLC懒汉式
    public static lazyMain getInstance(){

        if(lazymain==null)
        {
            synchronized (lazyMain.class)
            {
                if(lazymain==null)
                {
                    lazymain =new lazyMain();//不是一个原子性操作

                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     */
                }
            }
        }
        return lazymain;
    }

    //多线程并发测试，正常
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
             lazyMain.getInstance();
            }).start();
        }
    }

}
