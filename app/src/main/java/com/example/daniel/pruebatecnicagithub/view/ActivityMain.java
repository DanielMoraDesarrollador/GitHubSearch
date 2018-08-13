package com.example.daniel.pruebatecnicagithub.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.FragmentHelper;

import java.io.Serializable;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements FragmentBusqueda.NotificadorActivities {

    private FragmentBusqueda fragmentBusqueda;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentBusqueda = new FragmentBusqueda();
        fragmentManager = getSupportFragmentManager();

        FragmentHelper.cargarFragment(fragmentBusqueda, R.id.contenedor_de_fragments, fragmentManager);
    }

    @Override
    public void notificarRepo(List<Repositorio> repositorios, int posicion) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalle.REPO_KEY, (Serializable) repositorios);
        bundle.putInt(ActivityDetalle.POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
