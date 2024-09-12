package com.acm.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("envioCorreoSMTP")
public class EnvioCorreServidorSMTP implements IEnvioCorreos{
    @Value("${mail.smpt.own.host}")
    private String host;
    @Value("${mail.smpt.own.port}")
    private String port;
    @Value("${mail.smpt.own.user}")
    private String username;
    @Value("${mail.smpt.own.password}")
    private String password;

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public EnvioCorreServidorSMTP(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public EnvioCorreServidorSMTP() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void envioCorreos(String mensaje) {
        System.out.println("Envio correo mediante servidor propio");
    }
}
