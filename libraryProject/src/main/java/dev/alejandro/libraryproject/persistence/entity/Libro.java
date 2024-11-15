package dev.alejandro.libraryproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "\"libro\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro implements Serializable {
    @Serial
    private static final long serialVersionUID = -213219512412L;
    @Id
    @Column(name = "\"id_libro\"")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "\"titulo\"",length = 50)
    private String titulo;
    @Column(name = "\"anio_publicacion\"")
    private LocalDate anioPublicacion;
    @Column(name = "\"disponibilidad\"")
    private Boolean disponible;
    @Column(name = "\"descripcion\"")
    private String descripcion;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "\"id_autorfk\"", referencedColumnName = "\"id_autor\"")
    private Autor autor;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "\"id_categoriafk\"", referencedColumnName = "\"id_categoria\"")
    private Categoria categoria;
}

