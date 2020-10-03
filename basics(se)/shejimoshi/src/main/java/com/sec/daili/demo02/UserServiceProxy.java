package com.sec.daili.demo02;

public class UserServiceProxy implements UserService{

   private UserServiceImpl userService;

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public UserServiceProxy() {
    }

    public UserServiceProxy(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        setMsg("添加");
        userService.add();
    }

    @Override
    public void delete() {
        setMsg("删除");
        userService.delete();
    }

    @Override
    public void update() {
        setMsg("修改");
        userService.update();
    }

    @Override
    public void query() {
        setMsg("查询");
        userService.query();
    }


    public void setMsg(String msg)
    {
        System.out.println("[DeBug]==>使用了"+msg+"的方法");
    }
}
