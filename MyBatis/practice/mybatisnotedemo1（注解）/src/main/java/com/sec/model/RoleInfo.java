package com.sec.model;

import java.util.List;

/**
 * 角色表
 */
public class RoleInfo {
    private Integer role_id;
    private String role_name;
    //一个角色有多个员工
    private List<UserInfo> users;

    @Override
    public String toString() {
        return "RoleInfo{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", users=" + users +
                '}';
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
