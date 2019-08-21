package com.mzy.shejimoshi.JdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocation implements InvocationHandler {
    private JavaProxyInterface j;

    public ProxyInvocation() {
    }

    public ProxyInvocation(JavaProxyInterface j) {
        this.j = j;
    }

    public void aopMethod() {
        System.out.println("I am a aopMethod.");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aopMethod();
        Object invoke = method.invoke(j, args);
        return invoke;
    }
}
