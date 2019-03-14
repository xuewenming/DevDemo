package com.sam.dev;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sam
 * @date 2019/3/12
 * @time 8:21
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class DevTestTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置key,value
     * set key value
     */
    @Test
    public void testSetKeyValue() {
        redisTemplate.opsForValue().set("sam_001","20190314");
    }

    /**
     * 通过key获取value
     * get key
     */
    @Test
    public void testGetValue(){
        String str = (String) redisTemplate.opsForValue().get("sam_001");
        System.out.println(str);
    }

    /**
     * 删除key
     * delete key
     */
    @Test
    public void testDeleteKey() {
        redisTemplate.delete("sam_001");
    }

    /**
     * 求长度
     * strlength key value
     */
    @Test
    public void testLength() {
        Long sam_001 = redisTemplate.opsForValue().size("sam_001");
        System.out.println(sam_001);
    }

    /**
     * 设置新值并返回旧值
     * getset key value
     */
    @Test
    public void getSet() {
        String sam_001 = (String) redisTemplate.opsForValue().getAndSet("sam_001", "20190314AAAA");
        System.out.println(sam_001);
    }

    /**
     * 求子串
     * getrange key start end
     */
    @Test
    public void getRange() {
        String str = redisTemplate.opsForValue().get("sam_001",0,7);
        System.out.println(str);
    }

    /**
     * 追加
     * append key value
     */
    @Test
    public void testAppend() {
        Integer sam_001 = redisTemplate.opsForValue().append("sam_001", "11111111");
    }


}