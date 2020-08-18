package com.example.blog.service;

import com.example.blog.pojo.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
public interface DeptService extends IService<Dept> {
    List<Dept> selAllDpet(String dept_name);
}
