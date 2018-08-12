package com.example.daniel.pruebatecnicagithub.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repositorio implements Serializable {

    @SerializedName("name")
    private String nombreRepo;

    private String id;

    @SerializedName("owner")
    private PropietarioRepo propietarioRepo;

    @SerializedName("description")
    private String descripcion;

    @SerializedName("stargazers_count")
    private String cantidadDeEstrellas;

    @SerializedName("language")
    private String lenguaje;

    @SerializedName("forks_count")
    private int cantidadDeForks;

    public Repositorio() {
    }

    public String getNombreRepo() {
        return nombreRepo;
    }

    public String getId() {
        return id;
    }

    public PropietarioRepo getPropietarioRepo() {
        return propietarioRepo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCantidadDeEstrellas() {
        return cantidadDeEstrellas;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public int getCantidadDeForks() {
        return cantidadDeForks;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Repositorio)) {
            return false;
        } else {
            Repositorio repositorioAComparar = (Repositorio) obj;
            return repositorioAComparar.getId().equals(this.id);
        }
    }
}
