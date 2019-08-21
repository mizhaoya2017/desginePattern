package com.mzy.shejimoshi.PrototypePatternOfSelfBeanUtil;

public class Source {
    private String a;
    private int b;
    private InnerSource s;
    public void setA(String a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    public void setS(InnerSource s) {
        this.s = s;
    }
    public String getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public InnerSource getS() {
        return s;
    }
}
