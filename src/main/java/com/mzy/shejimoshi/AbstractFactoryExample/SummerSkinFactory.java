package com.mzy.shejimoshi.AbstractFactoryExample;

public class SummerSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SummerButton();
    }
    public TextField createTextField() {
        return new SummerTextField();
    }
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
