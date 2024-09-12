package com.acm.services.impl;

import com.acm.services.IEnvioCorreo;
import com.acm.utils.ILoggerService;

import java.util.logging.Level;
import java.util.stream.Stream;

public class EnvioCorreoSmptOutlook implements IEnvioCorreo {
    private ILoggerService loggerService;
    private String host;
    private String password;
    private int port;
    private final static String MESSAGE = "Envio de correo haciendo uso del servicio smtp de outlook";

    public EnvioCorreoSmptOutlook(){}

    public EnvioCorreoSmptOutlook(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public EnvioCorreoSmptOutlook(ILoggerService loggerService, String host, String password, int port) {
        this.loggerService = loggerService;
        this.host = host;
        this.password = password;
        this.port = port;
    }

    public EnvioCorreoSmptOutlook(String host, String password, int port) {
        this.host = host;
        this.password = password;
        this.port = port;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void enviarCorreo(String from, String to, String subject, String body, String[] cc) {
        var logger = loggerService.getLogger(EnvioCorreoSmptGmail.class.getName());
        var copias = String.join(",",cc);
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Cuerpo del mensaje: {4}
                Copias: {5}
                """, Stream.of(MESSAGE,from,to,subject,body,copias).toArray(String[]::new));
    }
    public void showConfig(){
        var logger = loggerService.getLogger(EnvioCorreoSmptGmail.class.getName());
        logger.log(Level.INFO, """
                Host: {0}
                Password: {1}
                Port: {2}
                """,Stream.of(host,password,String.valueOf(port)).toArray(String[]::new));
    }
    @Override
    public void enviarCorreo(String from, String to, String subject, String body) {
        var logger = loggerService.getLogger(EnvioCorreoSmptGmail.class.getName());
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Cuerpo del mensaje: {4}
                """, Stream.of(MESSAGE,from,to,subject,body).toArray(String[]::new));
    }
}
