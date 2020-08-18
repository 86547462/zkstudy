package com.sec.Conntroller;

import com.sec.model.UserInfo;
import com.sec.service.UserServiceImpl;
import com.sec.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/login")
public class ActionController {


    //service层
    @Autowired
     private UserServiceImpl service;
    @ResponseBody
    @RequestMapping(value = "save")
    /**
     * 添加信息
     */
    public String saveUser(UserInfo info){
        service.saveUser(info);
        return "保存成功..";
    }
    @RequestMapping(value = "sel")
    public ModelAndView findAll()
    {
        ModelAndView mv=new ModelAndView();
        List<UserInfo> list=service.findAll();
        mv.addObject("list",list);
        mv.setViewName("success");
        return mv;
    }



}
