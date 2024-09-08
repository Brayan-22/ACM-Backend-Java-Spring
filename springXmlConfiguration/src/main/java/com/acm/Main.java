package com.acm;

import com.acm.models.Cliente;
import com.acm.services.IEnvioCorreo;
import com.acm.services.impl.EnvioCorreoSmtpGmail;
import com.acm.utils.ILoggerService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Obtiene e imprime por pantalla la definicion de beans del contenedor de IoC
//        String[] beans = context.getBeanDefinitionNames();
//        for (String bean : beans) {
//            System.out.println(bean);
//        }
        IEnvioCorreo envioCorreo = context.getBean("envioCorreoGmail",IEnvioCorreo.class);
        Cliente cliente = context.getBean(Cliente.class);
        envioCorreo.enviarCorreo(cliente.getEmail(),"destino@correo.com","Asunto de correo prueba","Cuerpo de mensaje prueba");

        List<IEnvioCorreo> enviosCorreos = new ArrayList<>();
        //Se imprime por consola la referencia a memoria de cada bean, evidenciando el funcionamiento del scope prototype
        for (int i = 0;i<10;i++){
            enviosCorreos.add(context.getBean("envioCorreoOutlook",IEnvioCorreo.class));
        }
        enviosCorreos.forEach(System.out::println);


        enviosCorreos.get(0).enviarCorreo(cliente.getEmail(),"destino@correo.com","Asunto de correo prueba","Cuerpo de mensaje prueba");
    }
}