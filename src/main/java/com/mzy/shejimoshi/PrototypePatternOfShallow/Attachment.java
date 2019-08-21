package com.mzy.shejimoshi.PrototypePatternOfShallow;

public class Attachment {
    private String name;
    public void  setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void download() {
        System.out.println("下载附件，文件名为"+name);
    }
}
