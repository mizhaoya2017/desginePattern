package com.mzy.shejimoshi.JdkDynamicProxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        JavaProxyInterface c = new ConcreteClass();
        ProxyInvocation h = new ProxyInvocation(c);
        JavaProxyInterface proxy = (JavaProxyInterface)Proxy.newProxyInstance(JavaProxyInterface.class.getClassLoader(), new Class[]{JavaProxyInterface.class}, h);
        proxy.goToHome();
        proxy.goToSchool();
        proxy.oneDay();
        proxy.oneDayFinal();
    }
}
