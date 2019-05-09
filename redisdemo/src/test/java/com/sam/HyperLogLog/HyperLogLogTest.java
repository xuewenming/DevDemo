package com.sam.HyperLogLog;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 基数测试
 * @author Sam
 * @date 2019/5/9
 * @time 8:26
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class HyperLogLogTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test() {
        Long add = redisTemplate.opsForHyperLogLog().add("LogLog", "a", "b", "c", "d", "e");
        redisTemplate.opsForHyperLogLog().add("LogLog2","a");
        redisTemplate.opsForHyperLogLog().add("LogLog2","z");

        Long logLog = redisTemplate.opsForHyperLogLog().size("LogLog");
        System.out.println(logLog);

        Long logLog1 = redisTemplate.opsForHyperLogLog().size("LogLog1");
        System.out.println(logLog1);

        Long loglog_descA = redisTemplate.opsForHyperLogLog()
                .union("loglog_descA", "logLog", "logLog1");
        Long loglog_descA1 = redisTemplate.opsForHyperLogLog().size("loglog_descA");
        System.out.println(loglog_descA1);


    }



}
