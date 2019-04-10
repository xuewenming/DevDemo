package com.sam.list;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis-LinkedList
 * @author Sam
 * @date 2019/4/8
 * @time 8:25
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class TestList {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    private static final String key = "goods_0408";

    /**
     * 增
     */
    @Test
    public void testAdd() {
        /**
         * lpush key1 value1
         * rpush key1 value1
         */
        Long l001 = redisTemplate.opsForList().leftPush(key, "l001");
        Long r001 = redisTemplate.opsForList().rightPush(key, "r001");
        System.out.println(l001);
        System.out.println(r001);

        /**
         * lpush key1 value1 key2 value2
         */
        List<String> nodeList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            nodeList.add(i+"");
        }
        Long lAll = redisTemplate.opsForList().leftPushAll(key, nodeList);
        Long rAll = redisTemplate.opsForList().rightPushAll(key, nodeList);
        System.out.println(lAll);
        System.out.println(rAll);

        /**
         * linsert key before|after node1 node2
         */
        try {
            Long aLong = redisTemplate.getConnectionFactory().getConnection().lInsert(key.getBytes("utf-8"),
                    RedisListCommands.Position.AFTER, "1001".getBytes("utf-8"), "5001".getBytes("utf-8"));
            System.out.println(aLong);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * lpushx key node1
         */
        Long hahaha = redisTemplate.opsForList().leftPushIfPresent(key, "hahaha");
        System.out.println(hahaha);

        /**
         * rpushx key node1
         */
        Long nonono = redisTemplate.opsForList().leftPushIfPresent(key, "nonono");
        System.out.println(nonono);
    }

    @Test
    public void testUpdate() {
        /**
         * lset key value
         */
        redisTemplate.opsForList().set(key,5,"vvvvv");

        /**
         * ltrem key start end
         */
        redisTemplate.opsForList().trim(key,0,10);
    }

    @Test
    public void testDel(){
        /**
         * lpop key
         */
        String s = redisTemplate.opsForList().leftPop(key);
        System.out.println(s);
        /**
         * rpop key
         */
        String s1 = redisTemplate.opsForList().rightPop(key);
        System.out.println(s1);
        /**
         * lrem key count value
         */
        Long vvvvv = redisTemplate.opsForList().remove(key, 1, "vvvvv");
        System.out.println(vvvvv);

    }

    @Test
    public void testGet() {
        /**
         * lindex key index
         */
        String index = redisTemplate.opsForList().index(key, 0);
        System.out.println(index);

        /**
         * llen key
         */
        Long size = redisTemplate.opsForList().size(key);
        System.out.println(size);

        /**
         * lrange key start end
         */
        List<String> range = redisTemplate.opsForList().range(key, 0, 10);
        range.forEach(r -> {
            System.out.println(r);
        });
    }

    //-------------------------阻塞命令-----------------------------------------------------


    public final String k1 = "k1";

    public final String k2 = "k2";

    @Test
    public void test() {
        /**
         * blpop key timeout
         */
        String s = redisTemplate.opsForList().leftPop(k1, 2, TimeUnit.SECONDS);
        System.out.println(s);
        /**
         * brpop key timeout
         */
        String s1 = redisTemplate.opsForList().rightPop(k1, 2, TimeUnit.SECONDS);
        System.out.println(s1);
        /**
         * brpoplpush key sources desk
         */
        String s2 = redisTemplate.opsForList().rightPopAndLeftPush(k1, k2, 1, TimeUnit.SECONDS);
        System.out.println(s2);
        String s3 = redisTemplate.opsForList().rightPopAndLeftPush(k1, k2);
        System.out.println(s3);

    }


}
