package com.sam.dev;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 加法和减法
 * @author Sam
 * @date 2019/3/19
 * @time 8:33
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class IncrDecr {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void test() {
        redisTemplate.opsForValue().getAndSet("n", "10");
        printValue(redisTemplate, "n");

        redisTemplate.opsForValue().increment("n", 1L);
        printValue(redisTemplate, "n");

        redisTemplate.getConnectionFactory() .getConnection().decr(
                redisTemplate.getStringSerializer().serialize("n"));
        printValue(redisTemplate,"n");


        redisTemplate.opsForValue().increment("n", 2.3);
        printValue(redisTemplate,"n");


        /**
         * 获取原始的Jedis对象
         */
        RedisProperties.Jedis jedis = (RedisProperties.Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();


    }


    private void printValue(RedisTemplate redisTemplate, String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }


}
