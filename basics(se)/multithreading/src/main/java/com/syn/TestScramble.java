package com.syn;

/**
 *模拟抢票
 */
public class TestScramble implements Runnable{
    private int ticcket=20;
    //synchronized同步方法
    public synchronized void run() {
        while (true){
            if(ticcket<=0){
                break;
            }

            System.out.println(Thread.currentThread().getName()+"抢到了第"+ticcket--+"张票");

        }
    }

    public static void main(String[] args) {
        TestScramble testScramble=new TestScramble();
        new Thread(testScramble,"小明").start();
        new Thread(testScramble,"小花").start();
        new Thread(testScramble,"黄牛").start();
    }

}
