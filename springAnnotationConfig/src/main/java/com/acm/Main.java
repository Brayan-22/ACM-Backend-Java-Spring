package com.acm;

import com.acm.config.AppConfig;
import com.acm.models.Cliente;
import com.acm.services.IEnvioCorreo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext)context).register(AppConfig.class);
        context.refresh();
        IEnvioCorreo envioCorreo = context.getBean("envioCorreoGmail",IEnvioCorreo.class);
        envioCorreo.enviarCorreo("alejandro@correo.com",
                "destino@correo.com",
                "Correo de prueba",
                "Hola, este es el mensaje de un correo de prueba",
                List.of("correo1@correo.com","correo2@correo.com").toArray(String[]::new));
        IEnvioCorreo envioCorre2 = context.getBean("envioCorreoGmail",IEnvioCorreo.class);
        context.close();
    }
}