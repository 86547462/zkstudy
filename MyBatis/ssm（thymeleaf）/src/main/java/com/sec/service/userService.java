package com.sec.service;

import com.sec.model.UserInfo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface userService {
    public void saveUser(UserInfo info);
    public List<UserInfo> findAll();
}
