package com.example.daniel.pruebatecnicagithub.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.utils.FragmentHelper;

public class ActivityMain extends AppCompatActivity {

    private FragmentBusqueda fragmentBusqueda;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentBusqueda = new FragmentBusqueda();
        fragmentManager = getSupportFragmentManager();

        FragmentHelper.cargarFragment(fragmentBusqueda,R.id.contenedor_de_fragments,fragmentManager);
    }
}
