package com.acm.configuration;

import com.acm.services.EnvioCorreServidorSMTP;
import com.acm.services.EnvioCorreosGmail;
import com.acm.services.IEnvioCorreos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.acm"})
public class AppConfig {
    @Bean(value = {"envioCorreoGmail"})
    public IEnvioCorreos getEnvioCorreosGmail() {
        return new EnvioCorreosGmail();
    }

    @Bean(value = {"envioCorreoSMTP"})
    public IEnvioCorreos getEnvioCorreoServidorSMTP(){
        return new EnvioCorreServidorSMTP();
    }


}