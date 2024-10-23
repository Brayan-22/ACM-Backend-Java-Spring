package com.acm.programacionaspectos.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //Habilita el tejido de los aspectos mediante proxies dinámicos
public class AopConfig {
}
