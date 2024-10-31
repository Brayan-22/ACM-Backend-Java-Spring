package com.acm.apirestacm.presentation.dto;


import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;

}
