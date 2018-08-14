package com.example.daniel.pruebatecnicagithub.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.example.daniel.pruebatecnicagithub.model.dao.RepositorioDaoUtils;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.ResultListener;

import java.util.List;

public class RoomControllerRepo {

    private Context context;
    private RepositorioDaoUtils repositorioDaoUtils;

    public RoomControllerRepo(Context context) {
        this.context = context;
        repositorioDaoUtils = new RepositorioDaoUtils(context);
    }

    public void insertarRepositorios(List<Repositorio> lista) {
        if (hayInternet() && lista != null && lista.size() > 0) {
            repositorioDaoUtils.insertarListaRepos(lista);
        }
    }

    public void obtenerRepositorios(final ResultListener<List<Repositorio>> resultListenerVista) {
        if (!(hayInternet())) {
            repositorioDaoUtils.obtenerListaRepos(new ResultListener<List<Repositorio>>() {
                @Override
                public void finish(List<Repositorio> resultado) {
                    if (resultado != null && resultado.size() > 0) {
                        resultListenerVista.finish(resultado);
                    }
                }
            });
        }
    }

    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
