package com.acm.programacionaspectos.persistence.dao.impl;

import com.acm.programacionaspectos.persistence.dao.IDAO;
import com.acm.programacionaspectos.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductoDAO implements IDAO<Producto,Long> {
    private int countSaves;

    public ProductoDAO() {
        this.countSaves = 0;
    }
    @Override
    public Producto findById(Long aLong) {
        return null;
    }

    @Override
    public boolean save(Producto producto) throws Exception {
        System.out.println("Producto guardado correctamente");
        ++countSaves;
        if (countSaves > 4){
            throw new Exception("No se puede guardar el producto");
        }
        return false;
    }

    @Override
    public List<Producto> findAll() {
        return List.of(
                new Producto(1L,"Televisor",1200d),
                new Producto(2L,"Microondas",300d),
                new Producto(3L,"Consola",500d)
        );
    }
}
