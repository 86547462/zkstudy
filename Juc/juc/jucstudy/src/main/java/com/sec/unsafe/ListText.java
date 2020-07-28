package com.sec.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发下的list集合不安全，解决方案java.util.ConcurrentModificationException 并发修改异常
 *
 * 解决方案
 * 1.List list=new Vector();
 * 2.  List list =  Collections.synchronizedList(new ArrayList<>());
 * 3. List list = new CopyOnWriteArrayList();
 *
 * ***推荐使用第三种CopyOnWrite（写入时复制）
 * 读写分离
 * CopyOnWrite底层使用lock锁
 */
public class ListText {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(1,5));
                list.forEach(System.out::println);
            }).start();
        }

    }
}
