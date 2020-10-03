package chongxie;

public class Son extends Father{
    public void say1(){
        System.out.println("我是子类");

    }

    public static void main(String[] args) {
        Son son=new Son();
        son.say1();
        son.say2();
    }
}
