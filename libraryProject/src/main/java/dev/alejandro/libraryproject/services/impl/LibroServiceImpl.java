package dev.alejandro.libraryproject.services.impl;


import com.fasterxml.jackson.databind.JsonNode;
import dev.alejandro.libraryproject.persistence.entity.Libro;
import dev.alejandro.libraryproject.persistence.repository.AutorRepository;
import dev.alejandro.libraryproject.persistence.repository.CategoriaRepository;
import dev.alejandro.libraryproject.persistence.repository.LibroRepository;
import dev.alejandro.libraryproject.persistence.repository.PrestamoRepository;
import dev.alejandro.libraryproject.presentation.dto.ApiResponseDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.CreateLibroRequestDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.LibroResponseDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.UpdateLibroRequestDTO;
import dev.alejandro.libraryproject.services.ILibroService;
import dev.alejandro.libraryproject.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibroServiceImpl implements ILibroService {


    @Value("${app.openlibrary.url}")
    private String base_openLibrary;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public ApiResponseDTO<List<LibroResponseDTO>> findAll() {
        ApiResponseDTO<List<LibroResponseDTO>> response = new ApiResponseDTO<>();
        List<Libro> result = libroRepository.findAll();
        if (result.isEmpty()) {
            response.FailedOperation();
            return response;
        }
        var libros = result.stream().map(libro -> new LibroResponseDTO(
                libro.getTitulo(),
                libro.getAnioPublicacion(),
                libro.getDisponible(),
                libro.getDescripcion(),
                libro.getAutor().getNombre(),
                libro.getCategoria().getNombre()
        )).toList();
        response.SuccessOperation(libros);
        return response;
    }

    @Override
    public ApiResponseDTO<LibroResponseDTO> findByTitle(String title) {
        ApiResponseDTO<LibroResponseDTO> response = new ApiResponseDTO<>();
        var libro = libroRepository.findByTitulo(title);
        // if (libro.isEmpty()) {
        //     JsonNode jsonNode = null;
        //     Util.getJsonNode(base_openLibrary,"")
        // }
        //Su propia implementacion para consultar la API de OpenLibrary*********************************************Que es la finalidad del taller 2 :)
        if (libro.isEmpty()) {
            response.FailedOperation();
            return response;
        }
        var result = libro.get();
        response.SuccessOperation(new LibroResponseDTO(
                result.getTitulo(),
                result.getAnioPublicacion(),
                result.getDisponible(),
                result.getDescripcion(),
                result.getAutor().getNombre(),
                result.getCategoria().getNombre()
        ));
        return response;
    }

    @Override
    public ApiResponseDTO<List<LibroResponseDTO>> findByCategoria(String nombreCategoria) {
        ApiResponseDTO<List<LibroResponseDTO>> response = new ApiResponseDTO<>();
        var categoria = categoriaRepository.findByNombre(nombreCategoria);
        if (categoria.isEmpty() || categoria.get().getLibros().isEmpty()) {
            response.FailedOperation();
            return response;
        }
        var result = categoria.get().getLibros().stream().toList();
        var libros = result.stream().map(libro -> new LibroResponseDTO(
                libro.getTitulo(),
                libro.getAnioPublicacion(),
                libro.getDisponible(),
                libro.getDescripcion(),
                libro.getAutor().getNombre(),
                categoria.get().getNombre()
        )).toList();
        response.SuccessOperation(libros);
        return response;
    }

    @Override
    public ApiResponseDTO<List<LibroResponseDTO>> findByAutor(String nombreAutor) {
        ApiResponseDTO<List<LibroResponseDTO>> response = new ApiResponseDTO<>();
        var autor = autorRepository.findByNombre(nombreAutor);
        if (autor.isEmpty() || autor.get().getLibros().isEmpty()) {
            response.FailedOperation();
            return response;
        }
        var result = autor.get().getLibros().stream().toList();
        var libros = result.stream().map(libro -> new LibroResponseDTO(
                libro.getTitulo(),
                libro.getAnioPublicacion(),
                libro.getDisponible(),
                libro.getDescripcion(),
                autor.get().getNombre(),
                libro.getCategoria().getNombre()
        )).toList();
        response.SuccessOperation(libros);
        return response;
    }

    @Override
    public ApiResponseDTO<List<LibroResponseDTO>> findByPrestamo(Integer idCliente, Integer idPrestamo) {
        ApiResponseDTO<List<LibroResponseDTO>> response = new ApiResponseDTO<>();
        var prestamo = libroRepository.findByPrestamoAndCliente(idCliente, idPrestamo);
        if (prestamo.isEmpty()) {
            response.FailedOperation();
            return response;
        }
        log.info("Libros por prestamo: {}", prestamo);
        var libros = prestamo.stream().map(libro -> new LibroResponseDTO(
                libro.getTitulo(),
                libro.getAnioPublicacion(),
                libro.getDisponible(),
                libro.getDescripcion(),
                libro.getAutor().getNombre(),
                libro.getCategoria().getNombre()
        )).toList();
        response.SuccessOperation(libros);
        return response;
    }

    @Override
    public ApiResponseDTO<LibroResponseDTO> saveLibro(CreateLibroRequestDTO request) {
        ApiResponseDTO<LibroResponseDTO> response = new ApiResponseDTO<>();
        var autor = autorRepository.findById(request.getIdAutor());
        var categoria = categoriaRepository.findById(request.getIdCategoria());
        if (autor.isEmpty() || categoria.isEmpty()) {
            response.FailedOperation();
            return response;
        }
        Libro libro = Libro.builder()
                .titulo(request.getTitulo())
                .anioPublicacion(request.getAnioPublicacion())
                .disponible(request.getDisponible())
                .descripcion(request.getDescripcion())
                .autor(autor.get())
                .categoria(categoria.get())
                .build();
        var resultado = libroRepository.save(libro);
        response.SuccessOperation(new LibroResponseDTO(
                resultado.getTitulo(),
                resultado.getAnioPublicacion(),
                resultado.getDisponible(),
                resultado.getDescripcion(),
                autor.get().getNombre(),
                categoria.get().getNombre()
        ));
        return response;
    }

    @Override
    public ApiResponseDTO<LibroResponseDTO> updateLibro(String title, UpdateLibroRequestDTO libro) {
        return null;
    }
}
