package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
