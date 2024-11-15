package dev.alejandro.libraryproject.persistence.repository;

import dev.alejandro.libraryproject.persistence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Integer> {

}
