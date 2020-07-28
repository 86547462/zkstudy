package com.example.Service.ServiceImpl;

import com.example.Mapper.UserMapper;
import com.example.Service.UserService;
import com.example.pojo.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserService {

    @Autowired
    UserMapper mapper;
    @Override
    public userInfo userPwd(String user) {
        return mapper.userPwd(user);
    }
}
