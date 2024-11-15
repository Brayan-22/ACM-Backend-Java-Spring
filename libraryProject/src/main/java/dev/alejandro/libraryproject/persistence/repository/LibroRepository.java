package dev.alejandro.libraryproject.persistence.repository;

import dev.alejandro.libraryproject.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Integer> {
    @Query("""
        select p.libros_prestamo
        FROM Prestamo p
        JOIN p.cliente c on c.id = :idCliente
        WHERE p.id = :idPrestamo
    """)
    List<Libro> findByPrestamoAndCliente(Integer idCliente, Integer idPrestamo);

    Optional<Libro> findByTitulo(String titulo);
}