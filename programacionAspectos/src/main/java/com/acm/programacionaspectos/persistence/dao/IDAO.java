package com.acm.programacionaspectos.persistence.dao;

import java.util.List;

public interface IDAO <T,ID>{
    T findById(ID id);
    boolean save(T t) throws Exception;
    List<T> findAll();
}