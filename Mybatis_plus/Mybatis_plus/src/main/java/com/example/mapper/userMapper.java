package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * 在对应的mapper上面继承基本的类 baseMapper
 */

@Repository //代表持久层
public interface userMapper extends BaseMapper<User> {
    //所有CRUD操作已经编写完成
    //不需要配置一大堆文件

}
