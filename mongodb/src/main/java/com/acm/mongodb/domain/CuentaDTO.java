package com.acm.mongodb.domain;

import org.bson.Document;

public record CuentaDTO(String _id, String tipoCuenta, String banco, double saldo) {
    public Document getDocument() {
        Document document = new Document();
        document.append("_id", _id);
        document.append("tipoCuenta", tipoCuenta);
        document.append("banco", banco);
        document.append("saldo", saldo);
        return document;
    }

    public static CuentaDTO fromDocument(Document document) {
        return new CuentaDTO(document.getString("_id"), document.getString("tipoCuenta"), document.getString("banco"), document.getDouble("saldo"));
    }
}
