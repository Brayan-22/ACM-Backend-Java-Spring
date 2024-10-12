package com.acm.jdbcacm.persistence;
import java.sql.Connection;


public interface IConexion {
    Connection getConnection();
    void closeConnection(Connection connection);
}
