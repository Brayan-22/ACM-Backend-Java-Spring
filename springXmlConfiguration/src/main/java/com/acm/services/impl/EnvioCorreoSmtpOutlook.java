package com.acm.services.impl;

import com.acm.services.IEnvioCorreo;
import com.acm.utils.ILoggerService;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class EnvioCorreoSmtpOutlook implements IEnvioCorreo {
    private ILoggerService consoleLoggerService;
    private String host;
    private int puerto;
    private String password;

    public void setConsoleLoggerService(ILoggerService loggerService) {
        this.consoleLoggerService = loggerService;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return puerto;
    }

    public void setPort(int port) {
        this.puerto= port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnvioCorreoSmtpOutlook(ILoggerService loggerService) {
        this.consoleLoggerService = loggerService;
    }
    public EnvioCorreoSmtpOutlook(){}
    @Override
    public void enviarCorreo(String from, String to, String subject, String body, String[] cc) {
        var logger = consoleLoggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());
        var copias = String.join(",", cc);
        logger.log(Level.INFO, """
                Correo enviado usando el servicio SMTP de outlook
                Remitente: {0}
                Destinatario: {1}
                Asunto: {2}
                Mensaje: {3}
                Copias: {4}
                """, Stream.of(from,to,subject,body,copias).toArray(String[]::new));
    }

    public void showConfig(){
        var logger = consoleLoggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());
        var port = String.valueOf(puerto);
        logger.log(Level.INFO, """
                Servidor: {0}
                Contrasena: {1}
                Puerto: {2}
                """,Stream.of(host,password,port).toArray(String[]::new));
    }
    @Override
    public void enviarCorreo(String from, String to, String subject, String body) {
        var logger = consoleLoggerService.getLogger(EnvioCorreoSmtpOutlook.class.getName());

        logger.log(Level.INFO, """
                Correo enviado usando el servicio SMTP de outlook
                Remitente: {0}
                Destinatario: {1}
                Asunto: {2}
                Mensaje:{3}
                """, Stream.of(from,to,subject,body).toArray(String[]::new));
    }
}
