package com.acm.jdbcacm.persistence.nativeJdbc;

import com.acm.jdbcacm.persistence.IConexion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component("nativeConexion")
public class Conexion implements IConexion{
    @Value("${spring.datasource.url}")
    private String url;
    private final Properties properties;

    public Conexion() {
        this.properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "12345");
    }
    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,properties);
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
