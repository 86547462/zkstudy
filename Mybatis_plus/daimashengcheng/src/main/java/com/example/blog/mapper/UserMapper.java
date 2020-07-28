package com.example.blog.mapper;

import com.example.blog.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
@Component
public interface UserMapper extends BaseMapper<User> {
   List<User> selAll();
}
