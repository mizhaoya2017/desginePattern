package com.mzy.shejimoshi.FactoryMethodExample;

import com.mzy.shejimoshi.util.XMLUtil;

public class Client {
    public static void main(String[] args) {
       LoggerFactory factory;
       Logger logger;
       factory = (LoggerFactory) XMLUtil.getBean();
       logger = factory.createLogger();
       logger.writeLog();
    }
}
