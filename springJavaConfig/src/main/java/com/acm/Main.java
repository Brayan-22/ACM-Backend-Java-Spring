package com.acm;

import com.acm.config.AppConfig;
import com.acm.models.Cliente;
import com.acm.services.IEnvioCorreo;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context  = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext)context).register(AppConfig.class);
        context.refresh();
        IEnvioCorreo correo = context.getBean(IEnvioCorreo.class);
        Cliente cliente = context.getBean("clientePepito",Cliente.class);
        correo.enviarCorreo(cliente.getEmail(),"prueba@correo.com","Correo de prueba","Hola, este es el mensaje de un correo de prueba",
                Stream.of("correo1@correo.com","correo2@correo.com").toArray(String[]::new));

    }
}