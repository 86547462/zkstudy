package com.kun;

/**
 * 模拟龟兔赛跑
 */
public class TestTurtle implements Runnable{

    private static String Winner;
    public void run() {
        for (int i=0;i<=1000;i++){
            //模拟兔子睡觉
            if(Thread.currentThread().getName().equals("兔子")&&i%500==0)
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (gameOver(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑到了"+i+"步");
        }
    }


    public boolean gameOver(int steps){

        if(Winner!=null)//已经有胜利者了
        {
            return true;
        }else {
            if(steps>=1000)
            {
                Winner=Thread.currentThread().getName();
                System.out.println(Winner+"赢了");
            }
            return  false;
        }
    }

    public static void main(String[] args) {
        TestTurtle testTurtle=new TestTurtle();
        new Thread(testTurtle,"兔子").start();
        new Thread(testTurtle,"乌龟").start();
    }
}
