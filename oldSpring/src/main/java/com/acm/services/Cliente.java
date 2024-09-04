package com.acm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Cliente {

    private final IEnvioCorreos envioCorreos;
    public Cliente (@Autowired @Qualifier("envioCorreoSMTP") IEnvioCorreos envioCorreos) {
        this.envioCorreos = envioCorreos;
    }
    public void enviarCorreoServicio(){
        envioCorreos.envioCorreos("hola");
    }

}