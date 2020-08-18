package com.example.blog.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.pojo.User;
import com.example.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {


    @Resource(name = "userServiceImpl")
    private UserService service;

    //查看全部
    @ResponseBody
    @GetMapping("/findShowUser")
    public List<User> findShowUser() {
        return service.list();
    }

    //分页
    //查看全部
    @ResponseBody
    @GetMapping("/showPage/{cpage}/{limit}")
    public List<User> findShowPage(
            @PathVariable int cpage,
            @PathVariable int limit) {
        Page<User> page = new Page<>(cpage, limit);
        service.page(page, null);

        System.out.println("总页数" + page.getTotal());//总页数

        return page.getRecords();
    }

    //添加数据


    @PostMapping("/insertUser")
    public @ResponseBody String insertUser(User user) {

        boolean bool = service.save(user);

        if (bool) {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    //添加数据修改
    @PostMapping("/UpdateUser")
    public @ResponseBody String UpdateUser(User user) {

        boolean bool = service.updateById(user);

        if (bool) {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }


    //Swagger演示
    @ApiOperation(value = "Swagger添加用户示例", notes = "restful风格演示", httpMethod = "POST")
    @PostMapping("/saveUserApi")
    public @ResponseBody String insertUserApi(@ApiParam(name = "user",value = "传入一个对象") User user) {

        boolean bool = service.save(user);

        if (bool) {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }


    //自定义sql

    @GetMapping("/findUser")
    public @ResponseBody List<User> findUser() {

       return service.selAll();

    }





}