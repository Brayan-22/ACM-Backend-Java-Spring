package com.acm.services;

import org.springframework.stereotype.Service;

public class EnvioCorreServidorSMTP implements IEnvioCorreos{

    @Override
    public void envioCorreos(String mensaje) {
        System.out.println("Envio correo mediante servidor propio");
    }
}
