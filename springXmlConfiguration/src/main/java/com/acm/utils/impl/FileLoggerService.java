package com.acm.utils.impl;

import com.acm.utils.ILoggerService;
import java.io.IOException;
import java.util.logging.*;



public class FileLoggerService implements ILoggerService {
    @Override
    public Logger getLogger(String className) {
        var logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);
        try{
            FileHandler fh = new FileHandler("src/main/resources/app.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        }catch (IOException e){
            e.printStackTrace();
        }
        return logger;
    }
}
