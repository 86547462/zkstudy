package com.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class routerController {

    //首页
    @RequestMapping({"/","/index"})
    public  String index()
    {
        return "index";
    }

    //登录
    @RequestMapping("/toLogin")
    public  String toLogin()
    {
        return "views/login";
    }

    //切换页面
    @RequestMapping("/level1/{id}")
    public  String level1(@PathVariable("id") int id)
    {
        return "views/level1/"+id+"";
    }
    @RequestMapping("/level2/{id}")
    public  String level2(@PathVariable("id") int id)
    {
        return "views/level2/"+id+"";
    }
    @RequestMapping("/level3/{id}")
    public  String level3(@PathVariable("id") int id)
    {
        return "views/level3/"+id+"";
    }

}
