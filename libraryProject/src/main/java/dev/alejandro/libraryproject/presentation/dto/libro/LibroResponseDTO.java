package dev.alejandro.libraryproject.presentation.dto.libro;

import dev.alejandro.libraryproject.persistence.entity.Libro;

import java.time.LocalDate;

public record LibroResponseDTO(
        String titulo,
        LocalDate fechaPublicacion,
        Boolean disponible,
        String descripcion,
        String autor,
        String categoria
) {
}
