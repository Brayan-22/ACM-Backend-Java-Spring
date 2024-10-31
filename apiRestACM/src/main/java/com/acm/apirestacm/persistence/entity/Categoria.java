package com.acm.apirestacm.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id","nombre","impuesto"})
public class Categoria {
    @Id
    @Column(name = "idCategoria", nullable = false, unique = true)
    private Long id;
    private String nombre;
    private Double impuesto;

    public Categoria(Long id, String nombre, Double impuesto) {
        this.id = id;
        this.nombre = nombre;
        this.impuesto = impuesto;
    }

    /*
    * Relacion bidireccional muchos a muchos, [categoria (n) <--> producto(n)], varias categorias
    * agrupan varios prodcutos
    * Se especifica el nombre con el que es asociada la entidad actual en la otra entidad de la relación
    */

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Producto> productos = new HashSet<>();
}
