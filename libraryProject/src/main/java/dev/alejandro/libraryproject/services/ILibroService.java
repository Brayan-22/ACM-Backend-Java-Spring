package dev.alejandro.libraryproject.services;

import dev.alejandro.libraryproject.presentation.dto.ApiResponseDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.CreateLibroRequestDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.LibroResponseDTO;
import dev.alejandro.libraryproject.presentation.dto.libro.UpdateLibroRequestDTO;

import java.util.List;

public interface ILibroService {
    public ApiResponseDTO<List<LibroResponseDTO>> findAll();
    public ApiResponseDTO<LibroResponseDTO> findByTitle(String title);
    public ApiResponseDTO<List<LibroResponseDTO>> findByCategoria(String categoria);
    public ApiResponseDTO<List<LibroResponseDTO>> findByAutor(String autor);
    public ApiResponseDTO<List<LibroResponseDTO>> findByPrestamo(Integer idCliente ,Integer idPrestamo);
    public ApiResponseDTO<LibroResponseDTO> saveLibro(CreateLibroRequestDTO libro);
    public ApiResponseDTO<LibroResponseDTO> updateLibro(String title, UpdateLibroRequestDTO libro);
}
