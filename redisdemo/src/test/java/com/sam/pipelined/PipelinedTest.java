package com.sam.pipelined;

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
 * 测试流水线
 * @author Sam
 * @date 2019/6/6
 * @time 8:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PipelinedTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testPipelined() {
        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i < 1000; i++) {
                    int j = i + 1;
                    operations.boundValueOps("pipeline_key_" + j).set("pipeline_value_" + j);
                    operations.boundValueOps("pipeline_key_" + j).get();
                }
                return null;
            }
        };
        long startTime = System.currentTimeMillis();
        List<Object> objects = stringRedisTemplate.executePipelined(callback);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }


}
