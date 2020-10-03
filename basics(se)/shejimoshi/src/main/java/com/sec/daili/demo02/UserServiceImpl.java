package com.sec.daili.demo02;

/**
 * 真实对象
 */
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("实现了增加的操作...");
    }

    @Override
    public void delete() {
        System.out.println("实现了删除的操作...");
    }

    @Override
    public void update() {
        System.out.println("实现了修改的操作...");
    }

    @Override
    public void query() {
        System.out.println("实现了查询的操作...");
    }

    /**
     * 不能修改源代码，修改源代码大忌
     */
}
