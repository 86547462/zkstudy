package com.sec.Mapper;

import com.sec.model.UserInfo;

import java.util.List;

public interface userdao {
    public List<UserInfo> findByCondition(UserInfo info);
    public List<UserInfo> findByIds(List<Integer> ids);
    public List<UserInfo> findAll();
    public void deltetAll(List<Integer> id);
}
