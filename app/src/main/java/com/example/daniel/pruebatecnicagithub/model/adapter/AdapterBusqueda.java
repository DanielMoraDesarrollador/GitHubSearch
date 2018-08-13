package com.example.daniel.pruebatecnicagithub.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class AdapterBusqueda extends RecyclerView.Adapter {

    private List<Repositorio> listaRepos;
    private Context context;
    private Repositorio repo;
    private NotificadorBusquedaRepo notificadorBusquedaRepo;

    public AdapterBusqueda(Context context, NotificadorBusquedaRepo notificadorBusquedaRepo) {
        this.listaRepos = new ArrayList<>();
        this.context = context;
        this.repo = new Repositorio();
        this.notificadorBusquedaRepo = notificadorBusquedaRepo;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_repos, parent, false);
        ViewHolderRepo viewHolderRepo = new ViewHolderRepo(view);
        return viewHolderRepo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Repositorio repositorio = listaRepos.get(position);
        ViewHolderRepo viewHolderRepo = (ViewHolderRepo) holder;
        viewHolderRepo.armarCelda(repositorio);
    }

    @Override
    public int getItemCount() {
        if (listaRepos != null) {
            return listaRepos.size();
        } else {
            return 0;
        }
    }

    public void agregarRepos(List<Repositorio> resultado) {
        for (Repositorio repositorioAAgregar : resultado) {
            if (!this.listaRepos.contains(repositorioAAgregar)) {
                this.listaRepos.add(repositorioAAgregar);
            }
            notifyDataSetChanged();
        }
    }

    public void obtenerRepos(List<Repositorio> resultado) {
        if (resultado != null) {
            listaRepos = resultado;
        }
        notifyDataSetChanged();
    }

    public class ViewHolderRepo extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView cantidadDeEstrellas;
        private ImageView estrella;

        public ViewHolderRepo(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_repo);
            nombre.setSelected(true);
            cantidadDeEstrellas = itemView.findViewById(R.id.cantidad_estrellas);
            estrella = itemView.findViewById(R.id.imagen_estrella);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionRepoCliqueado = getAdapterPosition();
                    notificadorBusquedaRepo.notificarCeldaCliqueada(listaRepos, posicionRepoCliqueado);
                }
            });
        }

        public void armarCelda(Repositorio repositorio) {
            nombre.setText(repositorio.getNombreRepo());
            cantidadDeEstrellas.setText(repositorio.getCantidadDeEstrellas() + " Estrellas");
            estrella.setImageResource(R.drawable.ic_star_gold_24dp);
        }
    }

    public interface NotificadorBusquedaRepo {
        public void notificarCeldaCliqueada(List<Repositorio> repositorio, int posicion);
    }
}
