package com.example.Mapper;

import com.example.pojo.userInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    userInfo userPwd(String user);
}
