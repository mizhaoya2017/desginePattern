package com.mzy.shejimoshi.JdkDynamicProxy;

public class ConcreteClass implements JavaProxyInterface {
    @Override
    public void goToHome() {
        System.out.println("-------------goToHome-----------");
    }

    @Override
    public void goToSchool() {
        System.out.println("-------------goToSchool-----------");
    }

    @Override
    public void oneDay() {
        goToHome();
        goToSchool();
    }

    @Override
    public final void oneDayFinal() {
        goToHome();
        goToSchool();
    }
}
