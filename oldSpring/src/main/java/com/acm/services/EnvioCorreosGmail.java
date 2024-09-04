package com.acm.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class EnvioCorreosGmail implements IEnvioCorreos{
    @Override
    public void envioCorreos(String mensaje) {
        System.out.println("Envio de correo mediante SMTP de gmail");
    }
}
