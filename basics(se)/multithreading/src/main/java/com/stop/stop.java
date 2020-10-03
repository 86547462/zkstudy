package com.stop;

/**
 * 建议使用标志位进行终止变量
 */
public class stop implements Runnable{
    private  boolean flag=true;
    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println("线程进行了"+i++);
        }
    }
    public void breakup(){

        this.flag=false;
    }

    public static void main(String[] args) {
        stop stop=new stop();
        new Thread(stop).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //调用stop方法停止
        stop.breakup();
        System.out.println("线程终止了");

        }

}

