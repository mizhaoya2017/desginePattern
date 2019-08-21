package com.mzy.shejimoshi.PrototypePatternOfSpringBeanUtil;

import org.springframework.beans.BeanUtils;

public class Client {
    public static void main(String[] args) {
        Source m = new Source();
        m.setA("11");
        m.setB(1);
        InnerSource s = new InnerSource();
        s.setAa("22");
        m.setS(s);
        Source target = new Source();
        BeanUtils.copyProperties(m, target);
        System.out.println("m和target的属性a是否相同：" + (target.getA() == m.getA()));
        System.out.println("m和target的属性b是否相同：" + (target.getB() == m.getB()));
        System.out.println("m和target的属性s是否相同：" + (target.getS() == m.getS()));
        System.out.println("m和target是否相同：" + (target == m));
    }
}
