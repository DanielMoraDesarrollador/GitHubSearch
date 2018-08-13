package com.example.daniel.pruebatecnicagithub.model.dao;

import com.example.daniel.pruebatecnicagithub.model.pojo.ContenedorRepositorio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceRepositorio {

    @GET("search/repositories")
    Call<ContenedorRepositorio> obtenerRepositorios(@Query("q") String EditText,
                                                    @Query("sort") String stars,
                                                    @Query("order") String desc,
                                                    @Query("per_page")String cantidadElementos);
}
