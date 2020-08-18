package com.sec.Mapper;

import com.sec.model.RoleInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select * from role r,sysuserrole s where r.role_id=s.role_id and user_id=#{user_id}")
    public List<RoleInfo> rolelist(int id);
}
