package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query(value = "select c from Cliente c where c.nombre=:#{#nombre} and c.apellido=:apellido ")
    Optional<Cliente> findByNombreAndApellido(@Param("nombre") String nombre, @Param("apellido")String apellido);

    @Query(value = "select * from cliente c where c.apellido = ?1 limit 1",nativeQuery = true)
    Optional<Cliente> findFirstByApellido(String apellido);

    @Query(value = "select * from cliente c where c.nombre = :name limit 1",nativeQuery = true)
    Optional<Cliente> findFirstByNombre(@Param("name")String nombre);

    @Modifying
    @Transactional
    Integer deleteByNombre(String nombre);
}
