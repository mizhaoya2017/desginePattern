package com.mzy.shejimoshi.SimpleFactory;

public class Factory {
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProduct();
        }
        return product;
    }
}
