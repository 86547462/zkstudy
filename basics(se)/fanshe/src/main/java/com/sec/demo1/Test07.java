package com.sec.demo1;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 反射操作注解
 */
public class Test07 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        //通过反射获取注解
        Class c1=Class.forName("com.sec.demo1.Student");
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获取指定注解
        TableTab annotation = (TableTab) c1.getAnnotation(TableTab.class);
        System.out.println(annotation.value());//获取value值

        //获取指定类的指定注解
        Field name = c1.getDeclaredField("name");
       FieldValue fieldValue= name.getAnnotation(FieldValue.class);
        System.out.println(fieldValue.columnNmae());
        System.out.println(fieldValue.length());
        System.out.println(fieldValue.type());



    }
}

@TableTab("db_student")
class Student{

    @FieldValue(columnNmae = "name",type = "varchar",length = 250)
    private String name;
    @FieldValue(columnNmae = "age",type = "int",length = 11)
    private Integer age;
    @FieldValue(columnNmae = "address",type = "varchar",length = 250)
    private String address;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
    }

    public Student(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}

//类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableTab{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldValue{
    String columnNmae();
    String type();
    int length();
}