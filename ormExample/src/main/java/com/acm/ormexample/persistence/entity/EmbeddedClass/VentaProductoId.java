package com.acm.ormexample.persistence.entity.EmbeddedClass;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class VentaProductoId implements Serializable {
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(name = "id_venta")
    private Long idVenta;

    public VentaProductoId(Long idProducto, Long idVenta) {
        this.idProducto = idProducto;
        this.idVenta = idVenta;
    }
}
