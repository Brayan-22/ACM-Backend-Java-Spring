package com.acm.jdbcacm.persistence.datasourceJdbc;

import com.acm.jdbcacm.persistence.IConexion;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("datasourceConexion")
public class Conexion implements IConexion {

    private DataSource datasource;
    public Conexion(@Autowired DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
