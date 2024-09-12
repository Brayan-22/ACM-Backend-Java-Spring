package com.acm.configuration;

import com.acm.services.EnvioCorreServidorSMTP;
import com.acm.services.EnvioCorreosGmail;
import com.acm.services.IEnvioCorreos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"com.acm"})
@PropertySource("classpath:email.properties")
public class AppConfig {

    @Bean(value = {"envioCorreoGmail"})
    public IEnvioCorreos getEnvioCorreosGmail(@Value("${mail.smpt.gmail.host}")String host,
                                              @Value("${mail.smpt.gmail.port}")String port,
                                              @Value("${mail.smpt.gmail.password}") String password,
                                              @Value("${mail.smpt.gmail.user}") String username) {
        return new EnvioCorreosGmail(host, port, password, username);
    }

//    @Bean(value = {"envioCorreoSMTP"})
//    public IEnvioCorreos getEnvioCorreoServidorSMTP(){
//        return new EnvioCorreServidorSMTP();
//    }


}