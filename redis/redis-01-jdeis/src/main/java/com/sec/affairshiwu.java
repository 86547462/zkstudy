package com.sec;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 事务
 */
public class affairshiwu {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("106.55.168.234",6379);
        jedis.auth("123456");
        JSONObject json=new JSONObject();
        json.put("hello","word");
        json.put("你好","世界");
        String result=json.toJSONString();
        //开启事务
        Transaction multi=jedis.multi();

        try {
            multi.set("user1",result);
            multi.set("user2",result);

            multi.exec();  //执行事务

        }catch (Exception e)
        {  //放弃事务
            multi.discard();
            e.printStackTrace();

        }finally {

            System.out.printf(jedis.get("user1"));
            System.out.printf(jedis.get("user2"));
            //关闭连接
            jedis.close();

        }






    }



    /**
     * 乐观锁
     */


    @Test
    public  void leguansuo()
    {
        Jedis jedis=new Jedis("106.55.168.234",6379);
        jedis.auth("123456");

        jedis.watch("money");//监视money

        //在监视money后修改money的值
        //le();
        try {

            jedis.decrBy("money",100);
        }catch (Exception e)
        {
            jedis.unlink("money");//放弃监视
            e.printStackTrace();
            System.out.println(e.getMessage());

        }finally {

            System.out.println(jedis.get("money"));
            jedis.close();
        }


    }

    @Test
    public  void le()
    {
        Jedis jedis=new Jedis("106.55.168.234",6379);
        jedis.auth("123456");


        jedis.set("money","1000");
        jedis.get("money");
            jedis.close();


    }



}
