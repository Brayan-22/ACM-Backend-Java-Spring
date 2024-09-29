package com.acm.acmweb.persistencia.entity;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Pelicula {
    private int id;
    private String nombre;
    private String descripcion;
    private String lanzamiento;
    private String categoria;
}
