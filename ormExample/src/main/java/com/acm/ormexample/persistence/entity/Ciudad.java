package com.acm.ormexample.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"clientes","departamento"})
public class Ciudad {
    @Id
    @Column(name = "idCiudad", nullable = false, unique = true)
    private Long id;
    private String nombre;
    private Long codigoCiudad;

    /*
     * Relacion bidireccional uno a muchos [ciudad (1) <--> cliente (n)], una ciudad tiene varios clientes,
     * y un cliente pertenece a una ciudad
     * Se especifica el nombre con el que es asociada la entidad actual en la otra entidad de la relación
     */
    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();

    /*
     * Relacion bidireccional uno a muchos [ciudad (n) <--> departamento (1)], un departamento tiene varias ciudades,
     * y una ciudad pertenece a un departamento
     * La entidad "dueña" de la relacion es la que contendrá la clave foránea, en este caso ciudad
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_departamentoFK")
    private Departamento departamento;

    public Ciudad(Long id, String nombre, Long codigoCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.codigoCiudad = codigoCiudad;
    }
    public Ciudad(Long id, String nombre, Long codigoCiudad,Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.codigoCiudad = codigoCiudad;
        this.departamento = departamento;
    }
    public Ciudad(){
    }
}
