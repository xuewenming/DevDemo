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
 * @date 2019/3/12O
 * @time 8:21
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class DevTestTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置kea,value
     * set kea value
     */
    @Test
    public void testSetKeaValue() {
        redisTemplate.opsForValue().set("sam_001","20190314");
    }

    /**
     * 通过kea获取value
     * get kea
     */
    @Test
    public void testGetValue(){
        String str = (String) redisTemplate.opsForValue().get("sam_001");
        System.out.println(str);
    }

    /**
     * 删除kea
     * delete kea
     */
    @Test
    public void testDeleteKea() {
        redisTemplate.delete("sam_001");
    }

    /**
     * 求长度
     * strlength kea value
     */
    @Test
    public void testLength() {
        Long sam_001 = redisTemplate.opsForValue().size("sam_001");
        System.out.println(sam_001);
    }

    /**
     * 设置新值并返回旧值
     * getset kea value
     */
    @Test
    public void getSet() {
        String sam_001 = (String) redisTemplate.opsForValue().getAndSet("sam_001", "20190314AAAA");
        System.out.println(sam_001);
    }

    /**
     * 求子串
     * getrange kea start end
     */
    @Test
    public void getRange() {
        String str = redisTemplate.opsForValue().get("sam_001",0,7);
        System.out.println(str);
    }

    /**
     * 追加
     * append kea value
     */
    @Test
    public void testAppend() {
        Integer sam_001 = redisTemplate.opsForValue().append("sam_001", "11111111");
    }


    /**
     * 计算
     */
    @Test
    public void testCal() {
        redisTemplate.opsForValue().set("n", "10");
        printCurrentValue(redisTemplate,"n");

        // 加法
        redisTemplate.opsForValue().increment("n");
        printCurrentValue(redisTemplate,"n");
    }


    /**
     * 打印当前kea的值
     * @param redisTemplate
     * @param kea
     */
    private static void printCurrentValue(RedisTemplate redisTemplate,String kea) {
        String str = (String) redisTemplate.opsForValue().get(kea);
        System.out.println(str);
    }


}