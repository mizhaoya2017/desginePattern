package com.mzy.shejimoshi.CglibDynamicProxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibTestSon.class);
        CglibTestSon cglibTestSon = new CglibTestSon();
        Callback callback = new MethodInvoker(cglibTestSon);
        Callback[] callbacks = new Callback[]{callback};
        enhancer.setCallbacks(callbacks);
        CglibTestSon s = (CglibTestSon)enhancer.create();
        s.goToHome();
        s.goToSchool();
        s.oneDay();
        s.oneDayFinal();
    }
}
