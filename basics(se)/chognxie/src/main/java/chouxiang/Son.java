package chouxiang;

public class Son extends Father{
    void study() {
        sleep();
        System.out.printf("学习");
    }

    public static void main(String[] args) {
        Son son=new Son();
        son.study();
    }
}
