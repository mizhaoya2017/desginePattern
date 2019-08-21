package com.mzy.shejimoshi.CglibDynamicProxy;

public class CglibTestSon {
    public CglibTestSon() {

    }

    public void goToHome() {
        System.out.println("-------------goToHome-----------");
    }

    public void goToSchool() {
        System.out.println("-------------goToSchool-----------");
    }

    public void oneDay() {
        goToHome();
        goToSchool();
    }

    public final void oneDayFinal() {
        goToHome();
        goToSchool();
    }
}
