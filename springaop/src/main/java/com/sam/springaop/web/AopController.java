package com.sam.springaop.web;

import com.sam.springaop.aop.MyInvocationHandler;
import com.sam.springaop.pojo.Logtable;
import com.sam.springaop.service.DymicService;
import com.sam.springaop.service.LogtableService;
import com.sam.springaop.service.impl.DymicServicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

/**
 * @author Sam
 * @date 2019/5/16
 * @time 15:50
 */
@RestController
public class AopController {

    @Autowired
    private LogtableService logtableService;

    @Autowired
    private DymicService dymicService;

    @GetMapping("aop/test")
    public Object aop() {
        boolean b = logtableService.addLog(new Logtable());
        return b;
    }

    @GetMapping("dynamic/sayHello")
    public void sayHello() {
        DymicServicImpl impl = new DymicServicImpl();
        MyInvocationHandler handler = new MyInvocationHandler(impl);
        DymicService sy = (DymicService) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), handler);
        sy.sayHello();
    }
}
