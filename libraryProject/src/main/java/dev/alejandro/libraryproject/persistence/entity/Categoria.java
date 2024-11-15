package dev.alejandro.libraryproject.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"categoria\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"libros"})
public class Categoria implements Serializable {
    private static final long SerialVersionUID = -23124125135L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categoria")
    private Integer id;
    @Column(name = "\"nombre_categoria\"",length = 50)
    private String nombre;
    @Column(name = "\"descripcion_categoria\"")
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Libro> libros = new HashSet<>();
}
