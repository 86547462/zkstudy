package com.sec.daili.demo04;



public class Client {
    public static void main(String[] args) {
        //真实角色
        UserserviceImpl userservice=new UserserviceImpl();


        //代理角色
        ProxyInvocationHandler proxyInvocationHandler=new ProxyInvocationHandler();

        //通过调用程序来处理角色来处理我们要调用的对象！
        proxyInvocationHandler.setTarget(userservice);

       UserService userService= (UserService ) proxyInvocationHandler.getProxy();
        userService.add();
    }
}
