package com.example.daniel.pruebatecnicagithub.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalle extends AppCompatActivity {

    public static final String REPO_KEY = "repo_key";
    public static final String POSICION = "posicion_key";

    private List<FragmentDetalleRepo> fragmentDetalleRepos;
    private List<Repositorio> listaReposRecibidos;
    private ViewPager viewPagerRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        viewPagerRepo = findViewById(R.id.viewPager_repos);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        listaReposRecibidos = (List<Repositorio>) bundle.getSerializable(REPO_KEY);

        crearListaDeFragments();

        FragmentDetalleRepoPAdapter fragmentDetalleRepoPAdapter = new FragmentDetalleRepoPAdapter(
                getSupportFragmentManager(),
                fragmentDetalleRepos);

        viewPagerRepo.setAdapter(fragmentDetalleRepoPAdapter);
        viewPagerRepo.setPageTransformer(true, new DepthPageTransformer());

        int posicionDelItem = bundle.getInt(POSICION);
        viewPagerRepo.setCurrentItem(posicionDelItem);
    }

    private void crearListaDeFragments() {
        fragmentDetalleRepos = new ArrayList<>();
        for (Repositorio repositorio : listaReposRecibidos) {
            fragmentDetalleRepos.add(FragmentDetalleRepo.dameUnFragment(repositorio));
        }
    }
}
