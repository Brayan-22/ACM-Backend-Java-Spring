package com.acm.utils.impl;

import com.acm.utils.ILoggerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


@Component("fileLoggerService")
@Primary
public class FileLoggerService implements ILoggerService {


    @Override
    public Logger getLogger(String className) {
        var logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);
        try{
            FileHandler fileHandler = new FileHandler("src/main/resources/app.log",true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            return logger;
        }catch (IOException e){
            e.printStackTrace();
        }
        return logger;
    }
}
