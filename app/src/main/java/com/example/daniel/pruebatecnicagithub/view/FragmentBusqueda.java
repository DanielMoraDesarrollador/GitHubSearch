package com.example.daniel.pruebatecnicagithub.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.pruebatecnicagithub.R;
import com.example.daniel.pruebatecnicagithub.controller.ControllerRepositorio;
import com.example.daniel.pruebatecnicagithub.controller.RoomControllerRepo;
import com.example.daniel.pruebatecnicagithub.model.adapter.AdapterBusqueda;
import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;
import com.example.daniel.pruebatecnicagithub.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBusqueda extends Fragment implements AdapterBusqueda.NotificadorBusquedaRepo {

    private EditText editTextBusqueda;
    private ImageView imagenBusqueda;

    private RecyclerView recyclerViewBusqueda;
    private LinearLayoutManager linearLayoutManagerBusqueda;
    private AdapterBusqueda adapterBusqueda;

    private ControllerRepositorio controller;
    private NotificadorActivities notificadorActivities;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        editTextBusqueda = view.findViewById(R.id.editText_busqueda);
        imagenBusqueda = view.findViewById(R.id.logo_busqueda);
        adapterBusqueda = new AdapterBusqueda(getActivity(), this);
        linearLayoutManagerBusqueda = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewBusqueda = view.findViewById(R.id.recycler_busqueda);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity()
                , linearLayoutManagerBusqueda.getOrientation());
        recyclerViewBusqueda.addItemDecoration(dividerItemDecoration);

        setAdapterLinear(recyclerViewBusqueda, linearLayoutManagerBusqueda, adapterBusqueda);

        controller = new ControllerRepositorio(getActivity());
        editTextBusqueda.requestFocus();

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        editTextBusqueda.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    obtenerBusqueda();
                    editTextBusqueda.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextBusqueda.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        imagenBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerBusqueda();
                editTextBusqueda.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextBusqueda.getWindowToken(), 0);
            }
        });

        obtenerBusqueda();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorActivities = (NotificadorActivities) context;
    }

    private void setAdapterLinear(RecyclerView recyclerView,
                                  LinearLayoutManager linearLayoutManager,
                                  RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void obtenerBusqueda() {
        final RoomControllerRepo roomControllerRepo = new RoomControllerRepo(getContext());
        controller.obtenerBusquedaRepo(editTextBusqueda.getText().toString(), new ResultListener<List<Repositorio>>() {
            @Override
            public void finish(List<Repositorio> resultado) {
                if (resultado != null) {
                    adapterBusqueda.obtenerRepos(resultado);
                    roomControllerRepo.insertarRepositorios(resultado);
                } else {
                    roomControllerRepo.obtenerRepositorios(new ResultListener<List<Repositorio>>() {
                        @Override
                        public void finish(List<Repositorio> resultado) {
                            if (resultado != null) {
                                adapterBusqueda.obtenerRepos(resultado);
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public void notificarCeldaCliqueada(List<Repositorio> repositorios, int posicion) {
        notificadorActivities.notificarRepo(repositorios, posicion);
    }

    public interface NotificadorActivities {
        public void notificarRepo(List<Repositorio> repositorios, int posicion);
    }
}
