package com.example.daniel.pruebatecnicagithub.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.daniel.pruebatecnicagithub.model.dao.DaoRepositorio;
import com.example.daniel.pruebatecnicagithub.model.dao.RepositorioDaoUtils;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.ResultListener;

import java.util.List;

public class ControllerRepositorio {

    private Context context;
    private DaoRepositorio daoRepositorio;
    private String sort;
    private String order;
    private String per_page;


    public ControllerRepositorio(Context context) {
        this.context = context;
        this.daoRepositorio = new DaoRepositorio();
        this.sort = "stars";
        this.order = "desc";
        this.per_page = "50";
    }

    public void obtenerBusquedaRepo(String stringEditText, final ResultListener<List<Repositorio>> resultListenerBusqueda) {
        if (hayInternet()) {
            daoRepositorio.obtenerBusquedaRepo(stringEditText, new ResultListener<List<Repositorio>>() {
                @Override
                public void finish(List<Repositorio> resultado) {
                    resultListenerBusqueda.finish(resultado);
                }
            }, sort, order, per_page);
        } else {
            resultListenerBusqueda.finish(null);
        }
    }

    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void obtenerTodosLosRepositorios(final ResultListener<List<Repositorio>> resultListener) {
        RepositorioDaoUtils repositorioDaoUtils = new RepositorioDaoUtils(context);
        repositorioDaoUtils.obtenerListaRepos(new ResultListener<List<Repositorio>>() {
            @Override
            public void finish(List<Repositorio> resultado) {
                resultListener.finish(resultado);
            }
        });
    }
}
