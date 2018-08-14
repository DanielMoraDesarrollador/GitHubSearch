package com.example.daniel.pruebatecnicagithub.model.dao;


import com.example.daniel.pruebatecnicagithub.model.pojo.ContenedorRepositorio;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoRepositorio {

    private Retrofit retrofit;
    private ServiceRepositorio serviceRepositorio;

    public DaoRepositorio() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = retroBuilder.client(httpClient.build()).build();
        serviceRepositorio = retrofit.create(ServiceRepositorio.class);
    }

    public void obtenerBusquedaRepo(String stringEditText,
                                    final ResultListener<List<Repositorio>> resultListenerDelController,
                                    String stars,
                                    String order,
                                    String cantidadElementos) {

        Call<ContenedorRepositorio> call = serviceRepositorio.obtenerRepositorios(stringEditText, stars, order, cantidadElementos);
        call.enqueue(new Callback<ContenedorRepositorio>() {
            @Override
            public void onResponse(Call<ContenedorRepositorio> call, Response<ContenedorRepositorio> response) {
                ContenedorRepositorio contenedorRepositorioObtenido = response.body();

                if (contenedorRepositorioObtenido != null && contenedorRepositorioObtenido.getListaDeRepos() != null) {
                    List<Repositorio> listaRepositorios = contenedorRepositorioObtenido.getListaDeRepos();
                    resultListenerDelController.finish(listaRepositorios);
                } else {
                    resultListenerDelController.finish(new ArrayList<Repositorio>());
                }
            }

            @Override
            public void onFailure(Call<ContenedorRepositorio> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Repositorio>());
            }
        });
    }
}
