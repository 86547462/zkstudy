package com.sec.daili.demo01;

/**
 * 客户要租房
 */
public class Client {

    public static void main(String[] args) {
        //房东要出租房子
        landlord land=new landlord();
        //找到中介
        Rent agency=new agency(land);


        //不用面对房东，直接找中介租房就行
        agency.rent();

    }

}
