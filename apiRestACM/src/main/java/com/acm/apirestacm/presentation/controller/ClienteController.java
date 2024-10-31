package com.acm.apirestacm.presentation.controller;

import com.acm.apirestacm.presentation.dto.ApiResponseDTO;
import com.acm.apirestacm.presentation.dto.ClienteRequestDTO;
import com.acm.apirestacm.presentation.dto.ClienteResponseDTO;
import com.acm.apirestacm.services.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {



    private final ClienteService clienteService;


    @GetMapping("/hola")
    public ApiResponseDTO<String> getSaludo(){
        ApiResponseDTO<String> responseDTO = new ApiResponseDTO<>();
        responseDTO.setMessage("Hola");
        responseDTO.setTimestamp(LocalDate.now());
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Autentificado con exito");
        return responseDTO;
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ApiResponseDTO<ClienteResponseDTO> getCliente(@PathVariable Long id) {
        ApiResponseDTO<ClienteResponseDTO> responseDTO = new ApiResponseDTO<>();
        ClienteResponseDTO clienteResponseDTO = clienteService.getClienteById(id);
        responseDTO.setTimestamp(LocalDate.now());
        if (clienteResponseDTO == null ) {
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
            responseDTO.setMessage("Cliente no encontrado");
            responseDTO.setBody(null);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Cliente encontrado");
        responseDTO.setBody(clienteResponseDTO);
        return responseDTO;
    }

    @PostMapping("/save")
    public ApiResponseDTO<?> guardarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiResponseDTO<String> responseDTO = new ApiResponseDTO();
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Binding results errors");
            responseDTO.setBody(bindingResult.toString());
            responseDTO.setTimestamp(LocalDate.now());
            return responseDTO;
        }

        var bol = clienteService.saveCliente(clienteRequestDTO);
        ApiResponseDTO<Boolean> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        if (bol){
            responseDTO.setStatus(HttpStatus.CREATED);
            responseDTO.setMessage("Cliente guardado");
            responseDTO.setBody(true);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.NO_CONTENT);
        responseDTO.setMessage("Cliente no guardado");
        responseDTO.setBody(false);
        return responseDTO;
    }

    @GetMapping("/all")
    public ApiResponseDTO<List<ClienteResponseDTO>> getAllClientes(@RequestParam("cantidad") Integer cantidad) {
        ApiResponseDTO<List<ClienteResponseDTO>> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        long inicio = System.currentTimeMillis();
        List<ClienteResponseDTO> clientes = clienteService.getAllClientes(cantidad);
        long fin = System.currentTimeMillis();
        log.info("Tiempo de consulta: {}",fin-inicio);
        responseDTO.setBody(clientes);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Clientes encontrados");
        return responseDTO;
    }

}
