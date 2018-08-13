package com.example.daniel.pruebatecnicagithub.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.pruebatecnicagithub.R;

public class ActivityDetalle extends AppCompatActivity {

    public static final String REPO_KEY = "repo_key";
    public static final String POSICION = "posicion_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
    }
}
