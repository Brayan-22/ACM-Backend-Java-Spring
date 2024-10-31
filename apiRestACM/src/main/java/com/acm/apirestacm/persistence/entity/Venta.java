package com.acm.apirestacm.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@NoArgsConstructor
@ToString(of = {
        "id",
        "fecha"
})
public class Venta {
    @Id
    @Column(name = "idVenta",nullable = false,unique = true)
    private Long id;
    @Setter
    private LocalDate fecha;

    /*
     * Relacion bidireccional uno a muchos [cliente (1) <--> venta(n)], un cliente se presenta en varias ventas,
     * y una o varias ventas pertenecen unicamente a un cliente
     * La entidad "dueña" de la relacion es la que contendrá la clave foránea, en este caso venta
     */
    @ManyToOne
    @JoinColumn(name = "id_clienteFK")
    @Setter
    private Cliente cliente;


    @OneToMany(mappedBy = "venta",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    },fetch = FetchType.EAGER)
    private Set<VentaProducto> ventaProductos = new HashSet<>();



    public void addVentaProducto(VentaProducto ventaProducto) {
        ventaProductos.add(ventaProducto);
        ventaProducto.setVenta(this);
    }

    public Venta(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }
    public Venta(Long id, LocalDate fecha, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
    }
}
