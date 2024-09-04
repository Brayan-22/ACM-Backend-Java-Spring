package com.acm;

import com.acm.configuration.AppConfig;
import com.acm.models.Persona;
import com.acm.services.Cliente;
import com.acm.services.EnvioCorreServidorSMTP;
import com.acm.services.EnvioCorreosGmail;
import com.acm.services.IEnvioCorreos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    private Cliente cliente;
    public Main(@Autowired Cliente cliente) {
        this.cliente = cliente;
    }
    public void init(){
        cliente.enviarCorreoServicio();
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean(Main.class);
        main.init();
    }
}