package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}

