package com.sam.springaop.web;

import com.sam.springaop.SpringaopApplication;
import com.sam.springaop.pojo.Logtable;
import com.sam.springaop.service.LogtableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sam
 * @date 2019/5/16
 * @time 15:53
 */
@SpringBootTest(classes = SpringaopApplication.class)
@RunWith(SpringRunner.class)
public class AopControllerTest {

    @Autowired
    private LogtableService logtableService;

    @Test
    public Object testA() {
        boolean b = logtableService.addLog(new Logtable());
        return b;
    }
}