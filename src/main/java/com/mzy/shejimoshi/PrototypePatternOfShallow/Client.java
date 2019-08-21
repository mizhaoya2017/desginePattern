package com.mzy.shejimoshi.PrototypePatternOfShallow;

import java.nio.file.WatchEvent;

public class Client {
    public static void main(String [] args) {
        WeeklyLog log_previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        WeeklyLog log_new = log_previous.clone();
        System.out.println("周报是否相同："+(log_previous == log_new));
        System.out.println("附件是否相同："+(log_previous.getAttachment() == log_new.getAttachment()));
    }
}
