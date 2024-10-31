package com.acm.apirestacm.persistence.entity;


import com.acm.apirestacm.persistence.entity.EmbeddedClass.VentaProductoId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"producto"})
public class VentaProducto {

    @JsonIgnore
    @EmbeddedId
    private VentaProductoId ventaProductoId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("idVenta")//asociado al nombre del atributo de entidad de la llave compuesta
    @JoinColumn(name = "id_venta")//asociado al nombre de la anotacion @Column de la llave compuesta
    @JsonIgnore
    private Venta venta;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @MapsId("idProducto")//asociado al nombre del atributo de entidad de la llave compuesta
    @JoinColumn(name = "id_producto")//asociado al nombre de la anotacion @Column de la llave compuesta
    private Producto producto;

    private Integer cantidad;

    public VentaProducto(Venta venta, Producto producto, Integer cantidad) {
        this.ventaProductoId = new VentaProductoId(producto.getId(), venta.getId());
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        venta.addVentaProducto(this);
        producto.addVentaProducto(this);
    }
}
