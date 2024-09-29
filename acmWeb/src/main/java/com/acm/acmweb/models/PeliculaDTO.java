package com.acm.acmweb.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record PeliculaDTO(
        @SerializedName("Title") String nombre,
        @SerializedName("Plot") String descripcion,
        @SerializedName("Released") String lanzamiento) {
}