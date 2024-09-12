package com.acm.services;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Cliente {


    @Autowired
    @Qualifier("envioCorreoGmail")
    private IEnvioCorreos envioCorreos;

    public Cliente(){
    }
    public void enviarCorreoServicio(){
        System.out.println("Enviando Correo");
        envioCorreos.envioCorreos("hola");
    }

}