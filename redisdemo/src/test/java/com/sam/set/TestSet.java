package com.sam.set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * Redis中Set集合使用
 * @author Sam
 * @date 2019/4/19
 * @time 8:23
 */
@SpringBootTest
@RunWith(value = SpringRunner.class)
public class TestSet {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final String name1 = "set1";
    private final String name2 = "set2";

    @Test
    public void testAdd() {
        /**
         * sadd key val1 val2 val3
         */
        Long add = redisTemplate.boundSetOps(name1).add("v1", "v2", "v3", "v4", "v5");
        Long add1 = redisTemplate.boundSetOps(name2).add("v0", "v1", "v2", "v6", "v7");
        System.out.println(add);
        System.out.println(add1);
    }


    @Test
    public void testSelect() {
        /**
         * 求集合长度
         * sacrd key
         */
        Long size = redisTemplate.boundSetOps(name1).size();
        System.out.println(size);

        /**
         * 求差集
         * sdiff key1 key2
         */
        Set<String> difference = redisTemplate.opsForSet().difference(name1, name2);
        System.out.println(difference);

        /**
         * 求并集
         * sunion key1 key2
         */
        Set<String> union = redisTemplate.opsForSet().union(name1, name2);
        System.out.println(union);

        /**
         * 判断是否集合中的元素
         * sismember key member
         */
        Boolean v1 = redisTemplate.opsForSet().isMember(name1, "v1");
        System.out.println(v1);

        /**
         * 获取集合中的所有元素
         * smember key
         */
        Set<String> members = redisTemplate.opsForSet().members(name1);

        /**
         * 随机弹出一个元素
         * spop key
         */
        String pop = redisTemplate.opsForSet().pop(name1);
        System.out.println(pop);

        /**
         * 随机获取一个集合元素
         * srandmember key
         */
        String s = redisTemplate.opsForSet().randomMember(name1);
        System.out.println(s);

        /**
         * 随机获取2个集合元素
         *srandmember key1,key2
         */
        List<String> strings = redisTemplate.opsForSet().randomMembers(name1, 2L);
        strings.forEach(s->{
            System.out.println(s);
        });

        /**
         * 移除一个集合中的元素，参数可以是多个
         * srem key member1,member2
         */
        Long v11 = redisTemplate.opsForSet().remove(name1, "v1");
        System.out.println(v11);

        /**
         * 求两个集合的并集
         * sunion key1 key2
         */
        Set<String> union1 = redisTemplate.opsForSet().union(name1, name2);
        union1.forEach(u->{
            System.out.println(u);
        });

        /**
         * 求两个集合的并集，并保存到集合union_all中
         * sunion desc key1,key2
         */
        Long union_all = redisTemplate.opsForSet().unionAndStore(name1, name2, "union_all");
        System.out.println(union_all);

        /**
         * 求出两个集合的差集
         * sdiffstore desc key1,key2
         */
        Long sdiff_set = redisTemplate.opsForSet().differenceAndStore(name1, name2, "sdiff_set");
        System.out.println(sdiff_set);

        /**
         * 求出两个集合的交集
         * sinterstore desc key1 key2
         */
        Long sinter_set = redisTemplate.opsForSet().intersectAndStore(name1, name2, "sinter_set");
        System.out.println(sinter_set);


    }


}
