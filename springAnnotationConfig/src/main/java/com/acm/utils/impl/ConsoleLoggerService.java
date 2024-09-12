package com.acm.utils.impl;

import com.acm.utils.ILoggerService;
import org.springframework.stereotype.Component;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component("consoleLoggerService")
public class ConsoleLoggerService implements ILoggerService {
    @Override
    public Logger getLogger(String className) {
        var logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        return logger;
    }
}
