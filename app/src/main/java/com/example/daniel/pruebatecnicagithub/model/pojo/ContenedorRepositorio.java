package com.example.daniel.pruebatecnicagithub.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorRepositorio {

    @SerializedName("items")
    private List<Repositorio> listaDeRepos;

    public ContenedorRepositorio() {
    }

    public List<Repositorio> getListaDeRepos() {
        return listaDeRepos;
    }
}
