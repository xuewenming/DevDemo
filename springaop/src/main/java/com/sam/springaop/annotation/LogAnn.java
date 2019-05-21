package com.sam.springaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LOG注解
 * @author Sam
 * @date 2019/5/16
 * @time 15:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnn {
    // 记录日志操作类型
    String operateType();
}
