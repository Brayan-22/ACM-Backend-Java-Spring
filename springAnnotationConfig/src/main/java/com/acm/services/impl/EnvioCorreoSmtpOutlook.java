package com.acm.services.impl;

import com.acm.services.IEnvioCorreo;
import com.acm.utils.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.stream.Stream;

@Service("envioCorreoOutlook")
public class EnvioCorreoSmtpOutlook implements IEnvioCorreo {
    private ILoggerService loggerService;
    private String host;
    private String password;
    private int port;
    private final static String MESSAGE = "Envio de correo realizado mediante el servicio smtp de outlook";

    @Autowired
    @Qualifier("fileLoggerService")
    public void setLoggerService(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public String getHost() {
        return host;
    }

    @Value("${mail.smtp.outlook.host}")
    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    @Value("${mail.smtp.outlook.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    @Value("${mail.smtp.outlook.puerto}")
    public void setPort(int port) {
        this.port = port;
    }

    public void showConfig(){
        var logger = loggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());
        logger.log(Level.INFO, """
                Host: {0}
                Password: {1}
                Port: {2}
                """,Stream.of(host,password,String.valueOf(port)).toArray(String[]::new));
    }

    @Override
    public void enviarCorreo(String from, String to, String subject, String body, String[] cc) {
        var logger = loggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());
        var copias = String.join(",",cc);
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Mensaje: {4}
                Copias: {5}
                """,Stream.of(MESSAGE,from,to,subject,body,copias).toArray(String[]::new));
    }

    @Override
    public void enviarCorreo(String from, String to, String subject, String body) {
        var logger = loggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Mensaje: {4}
                """,Stream.of(MESSAGE,from,to,subject,body).toArray(String[]::new));
    }
}
