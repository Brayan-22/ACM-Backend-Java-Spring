package com.acm.jdbcacm.persistence.DAO;

import java.util.List;

public interface IDAO <T,ID>{
    T findById(ID id);
    List<T> findAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
