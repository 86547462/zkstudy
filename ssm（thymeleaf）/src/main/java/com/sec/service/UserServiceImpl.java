package com.sec.service;

import com.sec.Mapper.UserMapper;
import com.sec.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements userService {

    @Autowired
    private  UserMapper mapper;
    @Override
    public void saveUser(UserInfo info) {
        mapper.saveUser(info);
    }
    @Test
    @Override
    /**
     * service实现类
     * 查看全部
     */
    public List<UserInfo> findAll() {
        return  mapper.findAll();

    }
}
