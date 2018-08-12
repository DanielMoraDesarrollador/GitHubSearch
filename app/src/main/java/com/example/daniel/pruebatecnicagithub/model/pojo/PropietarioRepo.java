package com.example.daniel.pruebatecnicagithub.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PropietarioRepo implements Serializable {

    @SerializedName("login")
    private String Usuario;

    private String id;

    @SerializedName("avatar_url")
    private String imagenURL;

    public PropietarioRepo() {
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getId() {
        return id;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PropietarioRepo)) {
            return false;
        } else {
            PropietarioRepo propietarioRepoAComparar = (PropietarioRepo) obj;
            return propietarioRepoAComparar.getId().equals(this.id);
        }
    }
}
