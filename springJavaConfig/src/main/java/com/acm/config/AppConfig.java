package com.acm.config;

import com.acm.models.Cliente;
import com.acm.services.IEnvioCorreo;
import com.acm.services.impl.EnvioCorreoSmptGmail;
import com.acm.services.impl.EnvioCorreoSmptOutlook;
import com.acm.utils.ILoggerService;
import com.acm.utils.impl.ConsoleLoggerService;
import com.acm.utils.impl.FileLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:email.properties")
public class AppConfig {
    @Bean("consoleLoggerService")
    public ILoggerService getConsoleLoggerService() {
        return new ConsoleLoggerService();
    }
    @Bean("fileLoggerService")
    public ILoggerService getFileLoggerService() {

        return new FileLoggerService();
    }
    @Bean("envioCorreoSmtpGmail")
    @Primary
    public IEnvioCorreo getEnvioCorreoSmtpGmail(@Autowired @Qualifier("fileLoggerService") ILoggerService loggerService,
                                                @Value("${mail.smtp.gmail.host}")String host,
                                                @Value("${mail.smtp.gmail.password}") String password,
                                                @Value("${mail.smtp.gmail.puerto}") int port) {
        return new EnvioCorreoSmptGmail(loggerService, host, password, port);
    }

    @Bean("envioCorreoSmptOutlook")
    public IEnvioCorreo getEnvioCorreoSmtpOutlook(@Autowired @Qualifier("consoleLoggerService")ILoggerService loggerService,
                                                  @Value("${mail.smtp.outlook.host}") String host,
                                                  @Value("${mail.smtp.outlook.password}") String password,
                                                  @Value("${mail.smtp.outlook.puerto}") int port){
        return new EnvioCorreoSmptOutlook(loggerService, host, password, port);
    }
    @Bean("clientePepito")
    public Cliente getCliente(){
        return new Cliente(100L,"Pepito","Perez","pepito@correo.com","42141514");
    }
    @Bean
    @Primary
    @Scope("prototype")
    public Cliente getClienteGenerico(){
        return new Cliente();
    }
}
