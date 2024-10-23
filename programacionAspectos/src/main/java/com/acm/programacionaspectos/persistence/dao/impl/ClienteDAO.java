package com.acm.programacionaspectos.persistence.dao.impl;

import com.acm.programacionaspectos.persistence.dao.IDAO;
import com.acm.programacionaspectos.persistence.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@Repository
public class ClienteDAO implements IDAO<Cliente,Long> {

    private int countSaves;

    public ClienteDAO() {
        this.countSaves = 0;
    }
    @Override
    public Cliente findById(Long id) {
        try{
            Thread.sleep(Duration.ofSeconds(new Random().nextInt(1,6)).toMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return new Cliente(id,"hola","hola");
    }

    @Override
    public boolean save(Cliente cliente) throws Exception {
        System.out.println("ClienteDAO save succesfull");
        ++countSaves;
        if (countSaves > 4){
            throw new SQLException("ClienteDAO save failed");
        }
        return true;
    }


    @Override
    public List<Cliente> findAll() {
        return List.of(
                new Cliente(1L,"Fulano","Perez"),
                new Cliente(2L,"Brayan","Riveros"),
                new Cliente(3L,"Fulano","Ruiz")
        );
    }
}
