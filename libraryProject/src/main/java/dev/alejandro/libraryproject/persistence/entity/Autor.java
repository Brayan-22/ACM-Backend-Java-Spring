package dev.alejandro.libraryproject.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"autor\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"libros"})
public class Autor implements Serializable {
    @Serial
    private static final long serialVersionUID = -2132195121321L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"id_autor\"")
    private Integer id;
    @Column(name = "\"nombre_autor\"",length = 50)
    private String nombre;
    @Column(name = "\"pais_origen\"",length = 50)
    private String paisOrigen;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Libro> libros = new HashSet<>();

}
