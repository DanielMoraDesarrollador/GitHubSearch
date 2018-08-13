package com.example.daniel.pruebatecnicagithub.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity
public class Repositorio implements Serializable {

    @ColumnInfo(name = "nombreRepo")
    @SerializedName("name")
    private String nombreRepo;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_repositorio")
    private String id;

    @Ignore
    @SerializedName("owner")
    private PropietarioRepo propietarioRepo;

    @ColumnInfo(name = "descripcion")
    @SerializedName("description")
    private String descripcion;

    @ColumnInfo(name = "cantidadDeEstrellas")
    @SerializedName("stargazers_count")
    private String cantidadDeEstrellas;

    @ColumnInfo(name = "lenguaje")
    @SerializedName("language")
    private String lenguaje;

    @ColumnInfo(name = "forks")
    @SerializedName("forks_count")
    private String cantidadDeForks;

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

    public String getCantidadDeForks() {
        return cantidadDeForks;
    }

    public void setNombreRepo(String nombreRepo) {
        this.nombreRepo = nombreRepo;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setCantidadDeEstrellas(String cantidadDeEstrellas) {
        this.cantidadDeEstrellas = cantidadDeEstrellas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setCantidadDeForks(String cantidadDeForks) {
        this.cantidadDeForks = cantidadDeForks;
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
