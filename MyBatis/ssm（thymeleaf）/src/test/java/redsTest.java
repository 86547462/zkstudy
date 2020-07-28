import redis.clients.jedis.Jedis;

public class redsTest {
    public static void main(String[] args) {
        Jedis jedis= new Jedis("127.0.0.1",6379);
        jedis.set("test","测试");
        System.out.println(jedis.get("test"));
    }
}
