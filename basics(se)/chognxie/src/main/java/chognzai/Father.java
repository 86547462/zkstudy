package chognzai;

public class Father {
    public void sayHello() {
        System.out.println("Hello");
    }

    public void sayHello(String name) {
        System.out.println("Hello" + " " + name);
    }

    public static void main(String[] args) {
        Father father=new Father();
        father.sayHello();
    }
}
