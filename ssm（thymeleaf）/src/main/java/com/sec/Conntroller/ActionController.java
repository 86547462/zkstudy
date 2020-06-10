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





}
