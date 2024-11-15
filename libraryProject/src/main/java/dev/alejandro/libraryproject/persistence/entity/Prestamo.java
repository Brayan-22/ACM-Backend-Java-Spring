package dev.alejandro.libraryproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"prestamo\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prestamo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1124135L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "\"id_prestamo\"")
    private Integer id;
    @Column(name = "\"fecha_prestamo\"")
    private LocalDate fechaPrestamo;
    @Column(name = "\"fecha_devolucion\"")
    private LocalDate fechaDevolucion;

    @ManyToMany
    @JoinTable(
            name = "\"prestamo_libro\"",
            joinColumns = @JoinColumn(name = "\"id_prestamo\""),
            inverseJoinColumns = @JoinColumn(name = "\"id_libro\"")
    )
    private Set<Libro> libros_prestamo;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "\"id_clientefk\"",referencedColumnName = "\"id_cliente\"")
    private Cliente cliente;
}
