package dev.alejandro.libraryproject.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"cliente\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {
    @Serial
    private static final long serialVersionUID = 121412837213L;
    @Id
    @Column(name = "id_cliente")
    private Integer id;
    @Column(name = "nombre_cliente",length = 50)
    private String nombre;
    @Column(name = "correo_cliente",length = 50)
    private String correo;
    @Column(name = "telefono_cliente",length = 10)
    private String telefono;
    @Column(name = "estado_cuenta")
    private Boolean estadoCuenta;
    @OneToMany(mappedBy = "cliente",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private Set<Prestamo> clientes = new HashSet<>();
}
