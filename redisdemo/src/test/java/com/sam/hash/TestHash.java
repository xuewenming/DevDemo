package com.sam.hash;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Hash结构redisTemplate的使用
 * @author Sam
 * @date 2019/3/28
 * @time 8:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisdemoApplication.class)
public class TestHash {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testAdd() {
        /**
         * hmset key field1 value1 field2 value2
         */
        String key = "goods_003";
        Map<String,String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        map.put("c","C");
        redisTemplate.opsForHash().putAll(key,map);
        /**
         * hset key field1 value1
         */
        redisTemplate.opsForHash().put(key,"d","D");
        redisTemplate.opsForHash().put(key,"e","1");
        redisTemplate.opsForHash().put(key,"f","3.3");

        /**
         * hsetnx key field value
         */
        Boolean aBoolean = redisTemplate.opsForHash().putIfAbsent(key, "d", "DD");
        System.out.println(aBoolean);
    }

    @Test
    public void testGet() {
        /**
         * hgetall key
         */
        Map<Object, Object> mapA = redisTemplate.opsForHash().entries("goods_003");
        System.out.println(mapA);

        /**
         * hkeys key
         */
        Set<Object> goods_003 = redisTemplate.opsForHash().keys("goods_003");
        System.out.println(goods_003);

        /**
         * hvals key
         */
        List<Object> goods_0031 = redisTemplate.opsForHash().values("goods_003");
        System.out.println(goods_0031);

        /**
         * hmget key field1 field2
         */
        List<Object> keyList = new ArrayList<>();
        keyList.add("a");
        keyList.add("d");
        List<Object> goods_0032 = redisTemplate.opsForHash().multiGet("goods_003", keyList);
        System.out.println(goods_0032);
    }

    @Test
    public void testUpdate() {
        /**
         * hincrby key field increment
         */
        Long increment = redisTemplate.opsForHash().increment("goods_003", "e", 2L);
        System.out.println(increment);
        Double increment1 = redisTemplate.opsForHash().increment("goods_003", "e", 2.5);
        System.out.println(increment1);

        /**
         * hincrbyfloat key field incrment
         */
        Double increment2 = redisTemplate.opsForHash().increment("goods_003", "f", 2.5);
        System.out.println(increment2);
    }

    @Test
    public void testDelete() {
        /**
         * hdel key field1 field2
         */
        Long delete = redisTemplate.opsForHash().delete("goods_003", "a", "b");
        System.out.println(delete);
    }


}
