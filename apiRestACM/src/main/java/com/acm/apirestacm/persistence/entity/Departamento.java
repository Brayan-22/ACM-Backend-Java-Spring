package com.acm.apirestacm.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Departamento implements Serializable {
    @Id
    @Column(name = "idDepartamento", nullable = false, unique = true)
    private Long id;
    private String nombre;
    private Long codDepartamento;


    public Departamento(Long id, String nombre, Long codDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.codDepartamento = codDepartamento;
    }

    /*
     * Relacion bidireccional uno a muchos [departamento (1) <--> ciudad (n)], un departamento tiene varias ciudades,
     * y una ciudad pertenece a un departamento
     * Se especifica el nombre con el que es asociada la entidad actual en la otra entidad de la relación
     */
    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
    private Set<Ciudad> ciudades = new HashSet<>();

}
