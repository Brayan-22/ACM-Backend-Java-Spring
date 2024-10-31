package com.acm.apirestacm.services;

import com.acm.apirestacm.presentation.dto.ClienteRequestDTO;
import com.acm.apirestacm.presentation.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    public ClienteResponseDTO getClienteById(Long id);
    public boolean saveCliente(ClienteRequestDTO cliente);
    public List<ClienteResponseDTO> getAllClientes(Integer cantidadClientes);
}
