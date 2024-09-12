package com.acm;

import com.acm.configuration.AppConfig;
import com.acm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Main {
    @Value("#{10>11}")
    private boolean servidor;
    @Value("#{envioCorreoGmail.host}")
    private String hostSMPTGMail;

    private Cliente cliente;
    private IEnvioCorreos envioCorreos;
    public Main(@Autowired Cliente cliente, @Autowired @Qualifier("envioCorreoSMTP") IEnvioCorreos envioCorreos) {
        this.cliente = cliente;
        this.envioCorreos = envioCorreos;
    }
    public void init(){
        System.out.println(servidor);
        System.out.println(hostSMPTGMail);
        cliente.enviarCorreoServicio();
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean(Main.class);
        main.init();
    }
}