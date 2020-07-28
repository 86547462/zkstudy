package com.sec.Mapper;

import com.sec.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {
    public void saveUser(UserInfo info);
    public List<UserInfo> findAll();
}
