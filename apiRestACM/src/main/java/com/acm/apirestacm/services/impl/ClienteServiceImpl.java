package com.acm.apirestacm.services.impl;

import com.acm.apirestacm.persistence.entity.Cliente;
import com.acm.apirestacm.persistence.repository.ClienteRepository;
import com.acm.apirestacm.presentation.dto.ClienteRequestDTO;
import com.acm.apirestacm.presentation.dto.ClienteResponseDTO;
import com.acm.apirestacm.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository clienteRepository;


    @Override
    public ClienteResponseDTO getClienteById(Long id) {
        Optional<Cliente> c =  clienteRepository.findById(id);
        if (c.isPresent()) {
            return new ClienteResponseDTO(c.get().getNombre(),c.get().getApellido());
        }
        return null;
    }

    @Override
    public boolean saveCliente(ClienteRequestDTO cliente) {
        if ((cliente.getNombre() == null || cliente.getNombre().isBlank()) || (cliente.getApellido() == null || cliente.getApellido().isBlank())) {
            return false;
        }
        clienteRepository.save(new Cliente(cliente.getNombre(),cliente.getApellido()));
        return true;
    }
    @Override

    @Cacheable(value = "clientes",key = "#cantidadClientes",condition = "#cantidadClientes>100")
    public List<ClienteResponseDTO> getAllClientes(Integer cantidadClientes) {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(p -> new ClienteResponseDTO(p.getNombre(),p.getApellido())).toList().subList(0,cantidadClientes);
    }

}
