package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
