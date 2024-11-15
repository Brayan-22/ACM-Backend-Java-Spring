package dev.alejandro.libraryproject.presentation.dto.libro;

import dev.alejandro.libraryproject.utils.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateLibroRequestDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    private String titulo;
    @PastOrPresent
    @NotNull
    @Pattern(regexp = Constants.Formats.FORMAT_DATE_3)
    private LocalDate anioPublicacion;
    @NotNull
    private Boolean disponible;
    @NotBlank
    @Size(min = 10, max = 255)
    private String descripcion;
    @Positive
    private Integer idAutor;
    @Positive
    private Integer idCategoria;
}