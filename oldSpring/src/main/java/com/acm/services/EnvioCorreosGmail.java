package com.acm.services;

import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public class EnvioCorreosGmail implements IEnvioCorreos{

    private String host;
    private String port;
    private String username;
    private String password;

    @Override
    public String getHost() {
        return host;
    }

    public EnvioCorreosGmail() {
    }

    public EnvioCorreosGmail(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void envioCorreos(String mensaje) {
        System.out.println("Envio de correo mediante SMTP de gmail");
    }

}
