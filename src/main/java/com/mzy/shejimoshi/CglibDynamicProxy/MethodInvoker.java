package com.mzy.shejimoshi.CglibDynamicProxy;



import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class MethodInvoker implements MethodInterceptor {
    private CglibTestSon s;

    public MethodInvoker(CglibTestSon s) {
        this.s = s;
    }

    public void aopMethod() {
        System.out.println("I am a aopMethod.");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        aopMethod();
        Object invoke = method.invoke(s, args);
        return invoke;
    }
}
