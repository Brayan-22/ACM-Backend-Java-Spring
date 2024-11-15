package dev.alejandro.libraryproject.presentation.controller;

import dev.alejandro.libraryproject.presentation.dto.ApiResponseDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.CreateLibroRequestDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.LibroResponseDTO;
import dev.alejandro.libraryproject.services.ILibroService;
import dev.alejandro.libraryproject.utils.Constants;
import dev.alejandro.libraryproject.utils.Util;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = Constants.Global.API_BASE_PATH + Constants.Global.API_VERSION + Constants.Libro.LIBRO_SERVICE_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LibroController {


    private final ILibroService libroService;

    @GetMapping("/")
    public ResponseEntity<?> getAllLibros() {
        ApiResponseDTO<List<LibroResponseDTO>> response = libroService.findAll();
        if (Objects.isNull(response.getData()) ||response.getData().isEmpty()){
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @PostMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLibro(@Valid@RequestBody CreateLibroRequestDTO request, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(Util.getJson(Util.fieldsValidator(result)), HttpStatus.BAD_REQUEST);
        }
        ApiResponseDTO<LibroResponseDTO> response = libroService.saveLibro(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    /*
     * 
     * Otros request mappings :)
     */

}
