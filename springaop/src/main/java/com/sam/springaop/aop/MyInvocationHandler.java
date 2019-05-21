package com.sam.springaop.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Sam
 * @date 2019/5/20
 * @time 12:30
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target ) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("!!!!!!!!!!!!!!!!!!!Invoking sayHello!!!!!!!!!!!!!!!!!!!");
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
