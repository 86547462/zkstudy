package com.sec.daili.demo01;

/**
 * 房东
 */
public class landlord implements Rent{
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
