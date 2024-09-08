package com.acm.services.impl;

import com.acm.services.IEnvioCorreo;
import com.acm.utils.ILoggerService;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class EnvioCorreoSmtpGmail implements IEnvioCorreo {
    private Logger logger ;
    private String host;
    private String password;
    private int puerto;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public EnvioCorreoSmtpGmail(ILoggerService loggerService) {
        this.logger = loggerService.getLogger(EnvioCorreoSmtpGmail.class.getName());
    }
    @Override
    public void enviarCorreo(String from, String to, String subject, String body,String[] cc) {
        var copias = String.join(",", cc);
        logger.log(Level.INFO, """
                Correo enviado usando el servicio SMTP de gmail
                Remitente: {0}
                Destinatario: {1}
                Asunto: {2}
                Mensage: {3}
                CC: {4}
                """, Stream.of(from,to,subject,body,copias).toArray(String[]::new));
    }
    public void showConfig(){
        var port = String.valueOf(puerto);
        logger.log(Level.INFO, """
                Host: {0},
                Port: {1},
                Password: {2}
                """,Stream.of(host,port,password).toArray(String[]::new));
    }

    @Override
    public void enviarCorreo(String from, String to, String subject, String body) {
        logger.log(Level.INFO, """
                Correo enviado usando el servicio SMTP de gmail
                Remitente: {0}
                Destinatario: {1}
                Asunto: {2}
                Mensage: {3}
                """,Stream.of(from,to,subject,body).toArray(String[]::new));
    }
}
