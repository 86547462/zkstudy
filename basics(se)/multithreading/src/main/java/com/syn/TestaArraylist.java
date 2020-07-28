package com.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * list不安全,使用同步代码快加锁实现线程安全
 */
public class TestaArraylist {
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (list)
                {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
