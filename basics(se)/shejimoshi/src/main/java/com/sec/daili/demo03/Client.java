package com.sec.daili.demo03;

public class Client {
    public static void main(String[] args) {
        //真实角色
        landlord land=new landlord();


        //代理角色
        ProxyInvocationHandler proxyInvocationHandler=new ProxyInvocationHandler();

        //通过调用程序来处理角色来处理我们要调用的对象！
        proxyInvocationHandler.setRent(land);

       Rent rent= (Rent) proxyInvocationHandler.getProxy();
       rent.rent();
    }
}
