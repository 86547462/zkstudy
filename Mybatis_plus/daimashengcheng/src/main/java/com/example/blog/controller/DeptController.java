package com.example.blog.controller;


import com.example.blog.pojo.Dept;
import com.example.blog.service.DeptService;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
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
@RequestMapping("/blog/dept")
public class DeptController {

    @Resource(name = "deptServiceImpl")
    DeptService service;


    @ResponseBody
    @GetMapping("/findShowDept")
    public List<Dept> findShowDept()
    {
        return service.list();
    }

    /**
     * 一个部门有多个员工
     * @param dep_tname
     * @return
     */
    @GetMapping("/findShowDept/{dep_tname}")
    public @ResponseBody List<Dept> selAllDpet(
            @ApiParam(name="dep_tname",value = "传入一个部门名称")
            @PathVariable String dep_tname)
    {
        return service.selAllDpet(dep_tname);
    }

}

