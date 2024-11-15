package dev.alejandro.libraryproject.persistence.repository;

import dev.alejandro.libraryproject.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Integer> {
    Optional<Autor> findByNombre(String nombre);
}
