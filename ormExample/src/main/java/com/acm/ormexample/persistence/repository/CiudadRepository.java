package com.acm.ormexample.persistence.repository;

import com.acm.ormexample.persistence.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
