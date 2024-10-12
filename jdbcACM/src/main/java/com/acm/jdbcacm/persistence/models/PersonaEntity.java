package com.acm.jdbcacm.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "persona")
public class PersonaEntity implements Serializable {
    @Id
    private Integer id_persona;
    private String nombre_persona;
    private String correo_persona;

    public PersonaEntity(Integer id_persona, String nombre_persona, String correo_persona) {
        this.id_persona = id_persona;
        this.nombre_persona = nombre_persona;
        this.correo_persona = correo_persona;
    }
    public PersonaEntity() {}

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    @Override
    public String toString() {
        return "PersonaEntity{" +
                "id_persona=" + id_persona +
                ", nombre_persona='" + nombre_persona + '\'' +
                ", correo_persona='" + correo_persona + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return Objects.equals(id_persona, that.id_persona);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_persona);
    }
}
