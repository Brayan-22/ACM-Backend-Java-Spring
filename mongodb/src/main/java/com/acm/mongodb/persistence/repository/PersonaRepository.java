package com.acm.mongodb.persistence.repository;

import com.acm.mongodb.persistence.documents.Persona;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {

    @Query("{nombre: ?0}")
    @Update("{$set: {apellido: ?1}}")
    int updateApellidoByNombre(String nombre,String apellido);

    @Query("{id: ?0}")
    @Update("{$set: {nombre: ?#{[1].nombre},apellido:?#{[1].apellido},correo:?#{[1].correo},edad:?#{[1].edad}}}")
    int updatePersonaById(Integer id,Persona persona);

    @Query("{edad :  {$gt: ?0}}")
    List<Persona> findPersonaByEdadGreaterThan(Integer edad);


    @Aggregation(pipeline = {
            "{ $match: { '_id': ?0 } }",
            "{ $unwind: '$cuentas' }",
            "{ $match: { 'cuentas.tipoCuenta': ?1 } }",
            "{ $count: 'total' }"
    })
    long countTipoCuentaByIdPersona(Integer personaId, String tipoCuenta);

    @Query("{_id: ?0}")
    Persona findPersonaById(Integer id);

    @DeleteQuery("{_id: ?0}")
    int deletePersonaById(Integer id);

    @CountQuery("{nombre: ?0}")
    long countPersonasByNombre(String nombre);
}