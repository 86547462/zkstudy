package com.sec.daili.demo04;

public class UserserviceImpl implements UserService{
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

}
