package com.example.daniel.pruebatecnicagithub.model.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;

import java.util.List;

@Dao
public interface RepositorioDao {

    @Query("select * from repositorio")
    List<Repositorio> getListaRepos();

    @Insert
    void insertarRepos(List<Repositorio> repositorios);

    @Query("delete from repositorio")
    void eliminarRepositorios();
}
