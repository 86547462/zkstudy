package com.kun;

/**
 * 守护线程测试,
 * 虚拟机必须确保用户线程执行完毕，
 * 虚拟机不用等待守护线程执行完毕，
 */


/**
 * 二者其实基本上是一样的。唯一的区别在于JVM何时离开。
 * 用户线程：当存在任何一个用户线程未离开，JVM是不会离开的。
 * 守护线程：如果只剩下守护线程未离开，JVM是可以离开的。
 */
public class TestTaemon {

    public static void main(String[] args) {
        God god=new God();
        You you=new You();
        Thread godthread=new Thread(god);
        godthread.setDaemon(true);//设置上帝为守护线程（默认是false，用户线程）
        godthread.start();//上帝线程启动

        new Thread(you).start();//你启动

    }



}
//上帝(不停止)
class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑你");
        }

    }
}
//自己
class You implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 36000; i++) {
            System.out.println("你一生过得很开心");
        }
        System.out.println("你开心的去世了qwq.........");
    }
}