package com.sec.Mapper;

import com.sec.model.OrderInfo;
import com.sec.model.UserInfo;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

    //第一种方法
//    @Select("select * from orderinfo o,userinfo u where o.user_id=u.user_id")
//    @Results({
//            @Result(column = "order_id",property = "order_id"),
//            @Result(column = "order_time",property = "order_time"),
//            @Result(column = "total",property = "total"),
//            @Result(column = "user_id",property = "user.user_id"),
//            @Result(column = "user_name",property = "user.user_name"),
//            @Result(column = "user_age",property = "user.user_age")
//})
    //第二种方法
    @Select("select * from orderinfo")
    @Results({
            @Result(column = "order_id",property = "order_id"),
            @Result(column = "order_time",property = "order_time"),
            @Result(column = "total",property = "total"),
            @Result(
                    property = "user",//要疯装的名称,
                    column = "user_id",//根据那个字段去查userinfo的表（外键）
                    javaType = UserInfo.class,//要封装的实体类名
                    one = @One(select = "com.sec.Mapper.userdao.findByIds")//查询单条数据的类的位置

            )

    })
    //一对一（一个订单只有一个用户）
    public List<OrderInfo> selAll();


    //根据外键查全部（usereid）
    @Select("select * from orderinfo where user_id=(#{user_id})")
    public List<OrderInfo> selIds(int id);
}
