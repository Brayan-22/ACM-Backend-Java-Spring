package com.acm.utils.impl;

import com.acm.utils.ILoggerService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;



public class LoggerService implements ILoggerService {


    @PostConstruct
    public void init() {
        System.out.println("Metodo de inicio");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Metodo de destruccion");
    }

    public LoggerService() {
    }

    @Override
    public Logger getLogger(String className) {
        var logger = Logger.getLogger(className);
        try{
            FileHandler fh = new FileHandler("src/main/resources/app.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        }catch (IOException e){
            e.printStackTrace();
        }
        return logger;
    }
}
