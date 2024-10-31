package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClienteId(Long id);

    @Query(value = "select v from Venta v where v.id = ?1")
    Optional<Venta> findById(Long id);

}
