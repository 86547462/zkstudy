package com.sec.daili.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService=new UserServiceImpl();

        UserServiceProxy userServiceProxy=new UserServiceProxy();
        userServiceProxy.setUserService(userService);
        userServiceProxy.add();
        userServiceProxy.delete();
        userServiceProxy.update();
        userServiceProxy.query();
    }
}
