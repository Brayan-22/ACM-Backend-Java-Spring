package com.acm.mongodb.persistence.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "persona")
public class Persona {
    @MongoId(FieldType.INT64)
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer edad;
    private List<Cuenta> cuentas;
}


