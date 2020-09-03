package com.example.Controller;

import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class indeCcontroller {
    @RequestMapping({"/","/index"})
    public String index (Model model){
        model.addAttribute("msg","Hello,Shiro");
        return "index";
    }
    @RequestMapping("user/add")
    public  String add(){
        return "user/add";
    }
    @RequestMapping("user/update")
    public  String update(){
        return "user/update";
    }
    @RequestMapping("/tologin")
    public  String tologin(){
        return "login";
    }
    @RequestMapping("user/Unauthorized")
    public  String Unauthorized(){
        return "user/Unauthorized";
    }
    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST,params = {"user","pwd"})
    public  String login(String user,String pwd,Model model){
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token= new UsernamePasswordToken(user,pwd);

        try
        {
            subject.login(token);//执行登录方法，没有异常就ok了
            return "index";
        }catch (UnknownAccountException e){//用户不存在
            model.addAttribute("msg","用户不存在");
            return "login";
        }
        catch (IncorrectCredentialsException e){//密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
    //退出
    @RequestMapping("/quit")
    public String quit()
    {
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        try{
            subject.logout();
        }catch (SessionException ise){
            ise.printStackTrace();
        }
        return "index";
    }

}
