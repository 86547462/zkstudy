package com.sec;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestPing {

    public static void main(String[] args) {
        //1.new Jedis 对象即可
        Jedis jedis=new Jedis("106.55.168.234",6379);
        jedis.auth("123456");
        // Jedis的所有指令和redis指令一样
        System.out.println(jedis.ping());
        System.out.println("判断某个键是否存在"+jedis.exists("username"));//true和falues
        System.out.println("新增键值对"+jedis.set("username","zhangsan"));//返回ok
        System.out.println("系统中所有的键如下");// ;
        Set<String> keys=jedis.keys("*");
        System.out.println(keys);
        System.out.println(""+jedis.select(1));
        System.out.println("删除键password"+jedis.del("password"));
        System.out.println("查看username所存醋的值得类型"+jedis.type("username"));
        System.out.println("随机返回key空间的一个"+jedis.randomKey());
        System.out.println("重命名key"+jedis.rename("usernmae","name"));
        System.out.println("按索引查询"+jedis.select(0));
        System.out.println("删除当前数据库所有key"+jedis.flushDB());
        System.out.println("返回当前数据库的key的数目"+jedis.dbSize());
        System.out.println("删除所有数据库的key"+jedis.flushAll());

        jedis.close();//关闭连接
    }


}
