package com.sec.unsafe;

import java.util.ArrayList;
import java.util.List;

public class ListText1 {
    public static void main(String[] args) {
        List list=new ArrayList();
        for (int i = 0; i <= 1000; i++) {
            new Thread(()->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }

            }).start();
        }
        System.out.println(list.size());
    }
}
