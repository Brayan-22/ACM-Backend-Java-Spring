package dev.alejandro.libraryproject.presentation.dto.libro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UpdateLibroRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String titulo;
    @DateTimeFormat
    @PastOrPresent
    private LocalDate anioPublicacion;
    @NotBlank
    @Size(min = 3, max = 255)
    private String descripcion;
    @NotNull
    private Boolean disponible;
}