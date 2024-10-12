package com.acm.ormexample.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Cuenta {
    @Id
    @Column(name = "idCuenta", nullable = false, unique = true)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Setter
    private Privilegio privilegio;
    @Setter
    private String password;

    public Cuenta(UUID id,String password, Privilegio privilegio) {
        this.password = password;
        this.id = id;
        this.privilegio = privilegio;
    }

    public enum Privilegio{
        VIP,
        FRECUENTE,
        NOFRECUENTE;
    }
}