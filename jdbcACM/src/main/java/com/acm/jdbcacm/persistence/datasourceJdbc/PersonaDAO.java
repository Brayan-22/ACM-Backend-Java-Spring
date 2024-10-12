package com.acm.jdbcacm.persistence.datasourceJdbc;

import com.acm.jdbcacm.persistence.DAO.IDAO;
import com.acm.jdbcacm.persistence.IConexion;
import com.acm.jdbcacm.persistence.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("datasourcePersonaDao")
public class PersonaDAO implements IDAO<Persona,Integer> {

    private IConexion conexion;

    public PersonaDAO(@Autowired@Qualifier("datasourceConexion") IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Persona findById(Integer integer) {
        Connection con = conexion.getConnection();
        var sql = "SELECT * FROM persona WHERE id_persona = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Persona(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
            ps.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conexion.closeConnection(con);
        }
    }

    @Override
    public List<Persona> findAll() {
        return List.of();
    }

    @Override
    public void save(Persona persona) {

    }

    @Override
    public void update(Persona persona) {

    }

    @Override
    public void delete(Persona persona) {

    }
}
