package com.mzy.shejimoshi.FactoryMethodExample;

public class DatabaseLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        Logger logger = new DatabaseLogger();
        return logger;
    }
}
