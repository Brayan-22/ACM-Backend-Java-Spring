package com.acm.config;


import com.acm.models.Cliente;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.acm"})
@PropertySource({"classpath:email.properties"})
public class AppConfig {
}
