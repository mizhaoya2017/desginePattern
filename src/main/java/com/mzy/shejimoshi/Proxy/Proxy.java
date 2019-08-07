package com.mzy.shejimoshi.Proxy;

public class Proxy extends Subject {
    private RealSubject realSubject = new RealSubject();

    public void preRequest() {

    }

    @Override
    public void request() {
        preRequest();
        realSubject.request();
        postRequest();
    }

    public void postRequest() {

    }
}
