package com.sec.Conntroller;

import com.sec.model.UserInfo;
import com.sec.service.UserServiceImpl;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/login")
public class ActionController {


    //service层
    @Autowired
     private UserServiceImpl service;
    @Resource(name="redisTemplate")
    private RedisTemplate<String, UserInfo> redisTemplate; //注入Redis缓存
    private DebugLogger Log4j2Controller;

    /**
     * 添加信息
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public String saveUser(UserInfo info){
        service.saveUser(info);
        return "保存成功..";
    }
    @RequestMapping(value = "sel",method=RequestMethod.GET)
    public String findAll(Model model)
    {
        List<UserInfo> list=service.findAll();
        model.addAttribute("list", list);
        return "success";

    }
    @ResponseBody
    @RequestMapping(value = "saveRedis")
    public String saveUserRedis(UserInfo info){
        redisTemplate.opsForValue().set("test", info);
        Log4j2Controller.info("value："+redisTemplate.opsForValue().get("chen"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+redisTemplate.opsForValue().get("test"));
        return "保存成功..+"+redisTemplate.opsForValue().get("test")+"哈师大";
    }



}
