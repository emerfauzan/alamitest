package com.fauzan.alamitest.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CustomLogger {
    private Logger logger;

    public CustomLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void error(Exception ex){
        logger.error(ex.toString());
    }

    public void info(Exception ex){
        logger.info(ex.toString());
    }
}
