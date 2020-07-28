package com.sec.daili.demo01;

/**
 * 中介
 */
public class agency implements Rent{
    private landlord land;//房东的房源

    public landlord getLand() {
        return land;
    }

    public agency(landlord land) {
        this.land = land;
    }

    public void setLand(landlord land) {
        this.land = land;
    }

    @Override
    public void rent() {
        seeHouse();
        land.rent();
        fare();
    }

    /**
     * 中介提供的额外服务
     * 看房
     */
    public void seeHouse()
    {
        System.out.println("看房...");
    }

    public void fare()
    {
        System.out.println("收中介费...");
    }

}
