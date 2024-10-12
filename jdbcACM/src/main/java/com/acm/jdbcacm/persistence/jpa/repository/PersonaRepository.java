package com.acm.jdbcacm.persistence.jpa.repository;

import com.acm.jdbcacm.persistence.models.PersonaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer> {
    @Query(value = "select p from PersonaEntity p where p.nombre_persona=?1")
    PersonaEntity findByNombre(String nombre);

    @Query(value = "select * from persona",nativeQuery = true)
    List<PersonaEntity> getAllPersonas();
}
