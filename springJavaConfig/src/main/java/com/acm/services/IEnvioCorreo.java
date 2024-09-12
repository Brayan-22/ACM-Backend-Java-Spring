package com.acm.services;

public interface IEnvioCorreo {
    public void enviarCorreo(String from, String to, String subject, String body,String[] cc);
    public void enviarCorreo(String from,String to,String subject,String body);
}
