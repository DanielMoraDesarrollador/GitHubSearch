package com.example.daniel.pruebatecnicagithub.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleRepo extends Fragment {

    private static final String REPO_RECIBIDO = "repositorio_recibido";

    private ImageView imageView;
    private TextView nombreRepo;
    private TextView nombrePropietario;
    private TextView lenguaje;
    private TextView cantidadEstrellas;
    private TextView cantidadForks;
    private TextView descripcion;

    private Repositorio repositorio;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_repo, container, false);

        imageView = view.findViewById(R.id.imagen_detalle);
        nombreRepo = view.findViewById(R.id.nombre_repo_detalle);
        nombreRepo.setSelected(true);
        nombrePropietario = view.findViewById(R.id.nombre_propietario);
        lenguaje = view.findViewById(R.id.lenguaje);
        cantidadEstrellas = view.findViewById(R.id.cantidad_estrellas_detalle);
        cantidadForks = view.findViewById(R.id.cantidad_forks_detalle);
        descripcion = view.findViewById(R.id.descripcion);

        Bundle bundle = getArguments();
        repositorio = (Repositorio) bundle.getSerializable(REPO_RECIBIDO);

        Picasso.get()
                .load(repositorio.getPropietarioRepo().getImagenURL())
                .placeholder(R.drawable.placeholder)
                .into(imageView);
        nombreRepo.setText(repositorio.getNombreRepo());
        nombrePropietario.setText("Propietario: " + repositorio.getPropietarioRepo().getUsuario());
        lenguaje.setText("Lenguaje: " + repositorio.getLenguaje());
        cantidadEstrellas.setText(repositorio.getCantidadDeEstrellas() + " Estrellas");
        cantidadForks.setText("Forks: " + repositorio.getCantidadDeForks());
        descripcion.setText(repositorio.getDescripcion());

        return view;
    }

    public static FragmentDetalleRepo dameUnFragment(Repositorio repositorio) {
        FragmentDetalleRepo fragmentDetalleRepoCreado = new FragmentDetalleRepo();
        Bundle bundle = new Bundle();
        bundle.putSerializable(REPO_RECIBIDO, repositorio);
        fragmentDetalleRepoCreado.setArguments(bundle);
        return fragmentDetalleRepoCreado;
    }
}
