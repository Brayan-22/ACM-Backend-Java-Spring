package com.acm.jdbcacm.persistence.orm.repository;

import com.acm.jdbcacm.persistence.DAO.IDAO;
import com.acm.jdbcacm.persistence.models.PersonaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("entityManagerDAO")
public class PersonaDAO implements IDAO<PersonaEntity,Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PersonaEntity findById(Integer integer) {
        return em.find(PersonaEntity.class, integer);
    }

    @Override
    public List<PersonaEntity> findAll() {
        return List.of();
    }

    @Override
    public void save(PersonaEntity personaEntity) {

    }

    @Override
    public void update(PersonaEntity personaEntity) {

    }

    @Override
    public void delete(PersonaEntity personaEntity) {

    }
}
