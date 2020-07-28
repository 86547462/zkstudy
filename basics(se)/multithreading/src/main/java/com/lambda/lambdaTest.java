package com.lambda;
/**
 * 函数式接口
 */
interface Ilike {
    void lamdba();
}

/**
 * 实现类
 */
public class lambdaTest implements Ilike{
    public void lamdba() {
        System.out.println("我 爱你 ");
    }
}

/**
 * 静态内部类
 */
class Test {

    static public class lambdaTest1 implements Ilike{
        public void lamdba() {
            System.out.println("我 爱你(静态内部类) ");
        }
    }

    public static void main(String[] args) {
        Ilike ilike=new lambdaTest();
        ilike.lamdba();
        Ilike ilike1=new lambdaTest1();
        ilike1.lamdba();

        //局部内部类
         class lambdaTest2 implements Ilike{
            public void lamdba() {
                System.out.println("我 爱你(局部内部类) ");
            }
        }
        Ilike ilike2=new lambdaTest2();
         ilike2.lamdba();

         //匿名内部类，没有类名称，必须借助接口或者父类
        Ilike ilike3=new Ilike() {
            public void lamdba() {
                System.out.println("我 爱你(匿名内部类) ");
            }
        };
        ilike3.lamdba();

        //使用lamdba简化

        Ilike ilike4 = () ->{

                System.out.println("我 爱你(使用lamdba简化) ");

        };
        ilike4.lamdba();

    }

}


