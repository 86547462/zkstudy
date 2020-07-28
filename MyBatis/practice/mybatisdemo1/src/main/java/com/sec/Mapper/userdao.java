package com.sec.Mapper;

import com.sec.model.UserInfo;

import java.util.List;

public interface userdao {
    public List<UserInfo> findAll(UserInfo info);
}
