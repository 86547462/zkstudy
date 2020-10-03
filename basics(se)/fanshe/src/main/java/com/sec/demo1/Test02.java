package com.sec.demo1;

/**
 * 测试class类创建方式有哪些
 */
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException {
        //1.通过对象获得
        User user=new User();
        Class  c1 = user.getClass();

        System.out.println(c1.hashCode());
        //2.通过路径获得
        Class  c2 = Class.forName("com.sec.demo1.User");
        System.out.println(c2.hashCode());
        //3.通过类名.class
        Class  c3= User.class;
        System.out.println(c3.hashCode());
        //4.基本内置类型的包装类都有TYPE（特殊）
        Class c4=Integer.TYPE;
        System.out.println(c4.hashCode());


        //获取父类类型
        Class superclass = c1.getSuperclass();
        System.out.println(superclass);

    }
}
