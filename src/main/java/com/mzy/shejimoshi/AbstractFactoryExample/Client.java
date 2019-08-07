package com.mzy.shejimoshi.AbstractFactoryExample;

import com.mzy.shejimoshi.util.XMLUtil;

public class Client {
    public static void main(String[] args) {
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;
        factory = (SkinFactory) XMLUtil.getBean2();
        bt = factory.createButton();
        tf = factory.createTextField();
        cb = factory.createComboBox();
        bt.display();
        tf.display();
        cb.display();
    }
}
