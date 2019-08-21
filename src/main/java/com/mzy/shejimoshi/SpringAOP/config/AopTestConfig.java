package com.mzy.shejimoshi.SpringAOP.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Aspect
@Component
public class AopTestConfig implements Serializable {
    @Pointcut("execution(* com.mzy.shejimoshi.SpringAOP.service.AopTest.test(..))")
    public void aspect() {

    }

    @Before("aspect()")
    public void beforeCallback () {
        System.out.println("hello!");
    }
}
