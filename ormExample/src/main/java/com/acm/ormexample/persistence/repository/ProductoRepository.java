package com.acm.ormexample.persistence.repository;

import com.acm.ormexample.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
