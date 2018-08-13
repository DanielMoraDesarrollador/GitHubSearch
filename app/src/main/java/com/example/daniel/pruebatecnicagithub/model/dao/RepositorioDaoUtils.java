package com.example.daniel.pruebatecnicagithub.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.ResultListener;


import java.util.List;

public class RepositorioDaoUtils {

    private Context context;
    private AppDatabase appDatabase;

    public RepositorioDaoUtils(Context context) {

        this.context = context;
        this.appDatabase = AppDatabase.getInstance(context);
    }

    public void insertarListaRepos(List<Repositorio> repos) {
        InsertarListaReposTask insertarListaReposTask = new InsertarListaReposTask(repos);
        insertarListaReposTask.execute();
    }

    public void obtenerListaRepos(final ResultListener<List<Repositorio>> resultListenerController) {
        ObtenerListaReposTask obtenerListaReposTask = new ObtenerListaReposTask(resultListenerController);
        obtenerListaReposTask.execute();
    }


    private class InsertarListaReposTask extends AsyncTask<Void, Void, Void> {

        private List<Repositorio> repositorios;

        public InsertarListaReposTask(List<Repositorio> repos) {
            repositorios = repos;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Repositorio> repositorios1 = appDatabase.repositorioDao().getListaRepos();
            if (repositorios != null || repositorios.size() > 0) {
                if (repositorios1 == null || repositorios1.size() == 0)
                    appDatabase.repositorioDao().insertarRepos(repositorios);
            } else {
                appDatabase.repositorioDao().eliminarRepositorios();
                appDatabase.repositorioDao().insertarRepos(repositorios);
            }
            return null;
        }
    }

    private class ObtenerListaReposTask extends AsyncTask<Void, Void, List<Repositorio>> {

        private ResultListener<List<Repositorio>> resultListener;

        public ObtenerListaReposTask(ResultListener<List<Repositorio>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Repositorio> doInBackground(Void... voids) {
            return appDatabase.repositorioDao().getListaRepos();
        }

        @Override
        protected void onPostExecute(List<Repositorio> repositorios) {
            resultListener.finish(repositorios);
        }
    }
}
