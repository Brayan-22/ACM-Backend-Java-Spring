package com.acm.services.impl;

import com.acm.services.IEnvioCorreo;
import com.acm.utils.ILoggerService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.stream.Stream;

@Service("envioCorreoGmail")
public class EnvioCorreoSmtpGmail implements IEnvioCorreo {

    private Connection connection;
    @PreDestroy
    public void destroy(){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void init(){
        try{
            this.connection = DriverManager.getConnection("jdbc://mysql:");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private ILoggerService loggerService;
    private String host;
    private String password;
    private int port;
    private final static String MENSAJE_SERVICIO = "Correo enviado usando el servicio smtp de gmail";
//    public EnvioCorreoSmtpGmail (@Autowired ILoggerService loggerService){
//        this.loggerService = loggerService;
//    }

    @Autowired
    @Qualifier("consoleLoggerService")
    public void setLoggerService(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public String getHost() {
        return host;
    }

    public void setHost(@Value("${mail.smtp.gmail.host}") String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@Value("${mail.smtp.gmail.password}") String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(@Value("${mail.smtp.gmail.puerto}") int port) {
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
        var logger = loggerService.getLogger(EnvioCorreoSmtpGmail.class.getName());
        var copias = String.join(",", cc);
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Mensaje: {4}
                CC {5}
                """, Stream.of(MENSAJE_SERVICIO,from,to,subject,body,copias).toArray(String[]::new));
    }

    @Override
    public void enviarCorreo(String from, String to, String subject, String body) {
        var logger = loggerService.getLogger(EnvioCorreoSmtpGmail.class.getName());
        logger.log(Level.INFO, """
                {0}
                Remitente: {1}
                Destinatario: {2}
                Asunto: {3}
                Mensaje: {4}
                """,
                Stream.of(MENSAJE_SERVICIO,from,to,subject,body).toArray(String[]::new)
        );
    }
}
