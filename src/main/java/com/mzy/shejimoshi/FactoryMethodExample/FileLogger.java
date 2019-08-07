package com.mzy.shejimoshi.FactoryMethodExample;

public class FileLogger implements Logger {
    public void writeLog() {
        System.out.println("文件日志记录。");
    }
}
