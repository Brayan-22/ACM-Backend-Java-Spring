package com.acm.ormexample.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@ToString(exclude = {
        "ventas"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {
    @Id
    @Column(name = "idCliente", nullable = false,unique = true)
    private Long id;
    @Setter
    private String nombre;
    @Setter
    private String apellido;

    /*
    * Relacion bidireccional uno a muchos [cliente (1) <--> venta(n)], un cliente tiene varias ventas,
    * y una o varias ventas pertenecen unicamente a un cliente
    */
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Venta> ventas = new ArrayList<>();
    /*
    * Relacion bidireccional uno a muchos [cliente (n) <--> ciudad (1)], una ciudad tiene varios clientes,
    * y un cliente pertenece a una ciudad
    * La entidad "dueña" de la relacion es la que contendrá la clave foránea, en este caso cliente
    */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "id_ciudadFK")
    private Ciudad ciudad;


    /*
     * Relacion unidireccional uno a uno [cliente(1) --> cuenta(1)], solo la entidad cliente puede acceder a la información
     * de cuenta, pero no es el caso de la entidad cuenta con cliente
     * La entidad "dueña" de la relacion contendrá la clave foránea, en este caso cliente
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuentaFK", nullable = false,unique = true)
    private Cuenta cuenta;

    public Cliente() {

    }


    public void addVenta(Venta venta) {
        this.ventas.add(venta);
        venta.setCliente(this);
    }
}
