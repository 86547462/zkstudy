package com.sec.model;

import java.util.Date;

/**
 * 订单表
 */
public class OrderInfo {
    private Integer order_id;
    private Date order_time;
     private Double total;
     //订单属于哪一个用户
     private UserInfo user;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "order_id=" + order_id +
                ", order_time=" + order_time +
                ", total=" + total +
                ", user=" + user +
                '}';
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
