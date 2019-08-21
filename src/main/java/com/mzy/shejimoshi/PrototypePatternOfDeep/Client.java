package com.mzy.shejimoshi.PrototypePatternOfDeep;

public class Client {
    public static void main(String [] args) {
        WeeklyLog log_previous = new WeeklyLog();
        WeeklyLog log_new = null;
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        try {
            log_new = log_previous.deepClone();
        } catch (Exception e) {
            System.out.println("克隆失败");
        }
        System.out.println("周报是否相同："+(log_previous == log_new));
        System.out.println("附件是否相同："+(log_previous.getAttachment() == log_new.getAttachment()));
    }
}
