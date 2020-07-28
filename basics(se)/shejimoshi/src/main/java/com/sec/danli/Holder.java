package com.sec.danli;

/**
 * 静态内部类,线程安全
 */
public class Holder {
    private Holder(){

        System.out.println("静态内部类");
    }

    public static Holder getInstance(){
        return InnerClass.holder;
    }

    //静态内部类
    private static class InnerClass{

        private static final Holder holder=new Holder();

    }



    //测试
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                    Holder.getInstance();
        }).start();
        }

    }


}
