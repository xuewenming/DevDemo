package com.sam.springaop.aop;

import com.sam.springaop.annotation.LogAnn;
import com.sam.springaop.pojo.Logtable;
import com.sam.springaop.service.LogtableService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP 实现机制
 * @author Sam
 * @date 2019/5/16
 * @time 15:24
 */
@Component
@Aspect
public class LogAopAspect {

    @Autowired
    private LogtableService logtableService;

    @Around("@annotation(com.sam.springaop.annotation.LogAnn)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上的注解
        LogAnn annotation = method.getAnnotation(LogAnn.class);

        String value = annotation.operateType();
        Logtable log = new Logtable();
        Object result = null;

        try {
            result = pjp.proceed();
            System.out.println("---------------------正常----------------------");
        } catch (Throwable throwable) {
            System.out.println("--------------------异常-----------------------");
            throwable.printStackTrace();
        }finally {
            System.out.println("---------------------最终通知-------------------");
        }


        return result;

    }

}
