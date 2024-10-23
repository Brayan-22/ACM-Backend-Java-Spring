package com.acm.mongodb.domain;

import com.acm.mongodb.persistence.documents.Cuenta;
import org.bson.Document;

import java.util.List;

public record PersonaDTO(Integer id, String nombre, String apellido, String correo,Integer edad, List<CuentaDTO> cuentas) {
    public Document getDocument() {
        return new Document("_id", id).append("nombre", nombre)
                .append("apellido", apellido)
                .append("correo", correo)
                .append("edad", edad)
                .append("cuentas", cuentas);
    }
    public static PersonaDTO fromDocument(Document document) {
        return new PersonaDTO(document.getInteger("_id"),document.getString("nombre"),
                document.getString("apellido"),document.getString("correo"),
                document.getInteger("edad"),
                ((List<Document>)document.get("cuentas", List.class)).stream().map(CuentaDTO::fromDocument).toList());
    }
}
