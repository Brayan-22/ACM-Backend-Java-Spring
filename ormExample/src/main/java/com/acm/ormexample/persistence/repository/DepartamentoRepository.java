package com.acm.ormexample.persistence.repository;

import com.acm.ormexample.persistence.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
