package com.sam.transaction;

import com.sam.RedisdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Redis事务测试
 * @author Sam
 * @date 2019/5/21
 * @time 8:20
 */
@SpringBootTest(classes = RedisdemoApplication.class)
@RunWith(SpringRunner.class)
public class transactionTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试事务
     */
    @Test
    public void test1() {
       SessionCallback callback = new SessionCallback() {
           @Override
           public Object execute(RedisOperations ops) throws DataAccessException {
               // 开启事务
               ops.multi();
               // 事务进入队列
               ops.boundValueOps("a").set("aaaaa");
               // 命令只是进入队列，而没有被执行，使用get命令，返回的是空
               Object a = ops.boundValueOps("a").get();
               System.out.println(a);
               // 执行事务
               List exec = ops.exec();
               // 事务执行后，获取值
               Object a1 = ops.boundValueOps("a").get();
               System.out.println(a1);
               return a1;
           }
       };
        Object execute = stringRedisTemplate.execute(callback);
        System.out.println(execute);
    }


    /**
     * 测试数据结构错误，事务是否回滚
     */
    @Test
    public void test2() {

    }

}
