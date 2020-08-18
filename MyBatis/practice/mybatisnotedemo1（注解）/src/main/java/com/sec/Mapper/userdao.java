package com.sec.Mapper;

import com.sec.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface userdao {
    @Select("select * from userinfo")
    public List<UserInfo> findAll();//查全部
    @Select("select * from userinfo where user_id=(#{user_id})")
    public List<UserInfo> findByIds(int id);//根据id查询
    @Delete("delete from userinfo where user_id=(#{user_id})")
    public void deleteUser(int id);//删除
    @Insert("insert into userinfo values(null,user_name=#{user_name},user_age=#{user_age})")
    public void insertUser(UserInfo info);//添加
    @Update(" update userinfo set user_name=(#{user_name}),user_age=(#{user_age}) where user_id=(#{user_id})")
    public void updateUser(UserInfo info);//修改



    //一对多（一个用户有多个订单）
    @Select("select * from userinfo")
    @Results({
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_age",property = "user_age"),
            @Result(
                    property = "orders",//集合名
                    column = "user_id",//主键名，根据哪一个字段查询
                    javaType = List.class,//查询结果类型
                    many = @Many(select = "com.sec.Mapper.OrderDao.selIds")
            )
    })
    public List<UserInfo> selUserOrderAll();


    /**
     * 多堆多
     * @return
     */
    @Select("select * from userinfo")
    @Results({
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_age",property = "user_age"),
            @Result(
                    property = "roles",//集合名
                    column = "user_id",//主键名，根据哪一个字段查询
                    javaType = List.class,//查询结果类型
                    many = @Many(select = "com.sec.Mapper.RoleDao.rolelist")

            )
    })
    //多堆多
    public List<UserInfo> selUserRole();

    //多堆多(错误)
    @Select("select * from userinfo u,role r,sysuserrole s where u.user_id=s.user_id and r.role_id=s.role_id")

    public List<UserInfo> selUserRole2();
}
