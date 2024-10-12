package com.acm.ormexample.persistence.entity;


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
@ToString(of = {"id","nombre","precio","categorias"})
public class Producto {
    @Id
    @Column(name = "idProducto", nullable = false, unique = true)
    private Long id;
    private String nombre;
    private Double precio;
    /*
     * Relacion bidireccional muchos a muchos, [producto (n) <--> categoria(n)], un producto puede tener varias
     * categorias y una categoria puede estar presente en varios productos
     * Se utiliza la anotacion @JoinTable, que se encarga de crear la tabla de rompimiento en la base
     * de datos, sin la necesidad de crear una entidad que la mapee directamente en nuestro programa
     * (Tabla ProductoCategoria)
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "ProductoCategoria",
            joinColumns = {@JoinColumn(name = "idProducto")},//nombre de la clave foranea asociada al id de esta entidad,
            // que se creará en la tabla de rompimiento
            inverseJoinColumns = {@JoinColumn(name = "idCategoria")}//nombre de la clave foranea asociada al id de la otra entidad de la relacion,
            // que se creará en la tabla de rompimiento
    )
    private Set<Categoria> categorias = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "producto",cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<VentaProducto> ventaProductos = new HashSet<>();

    public Producto(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
        categoria.getProductos().add(this);
    }

    public void addVentaProducto(VentaProducto ventaProducto) {
        ventaProductos.add(ventaProducto);
        ventaProducto.setProducto(this);
    }

}
