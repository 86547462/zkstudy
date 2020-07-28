package com.example.blog.service;

import com.example.blog.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
public interface UserService extends IService<User> {
    List<User> selAll();
}
