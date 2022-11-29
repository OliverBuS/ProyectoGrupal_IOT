package com.example.proyectogrupal_iot.cliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectogrupal_iot.ClienteEquipoActivity;
import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.adapter.EquiposAdapter;
import com.example.proyectogrupal_iot.databinding.FragmentClienteEquiposBinding;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.interfaces.RecycleviewerInterface;
import com.example.proyectogrupal_iot.save.ClienteSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ClienteEquiposFragment extends Fragment implements RecycleviewerInterface {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FragmentClienteEquiposBinding binding;
    RecyclerView recyclerView;
    EquiposAdapter equiposAdapter;
    TextView textMarca, textDispositivo;
    LinearLayout vistafiltros, labelMarca, labelDispositivo;
    Context context;

    private boolean notCreated = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentClienteEquiposBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("equipos");
        View view = inflater.inflate(R.layout.fragment_cliente_equipos, container, false);
        recyclerView = view.findViewById(R.id.recycleViewer);
        labelDispositivo = view.findViewById(R.id.labelDispositivo);
        labelMarca = view.findViewById(R.id.labelMarca);
        vistafiltros = view.findViewById(R.id.vistaFiltros);
        textDispositivo = view.findViewById(R.id.textoDispositivo);
        textMarca = view.findViewById(R.id.textMarca);

        labelMarca.setOnClickListener(v -> {
            ClienteSession.setMarcaFiltro("");
            reloadList();
        });

        labelDispositivo.setOnClickListener(v -> {
            ClienteSession.setDispositivoFiltro("");
            reloadList();
        });


        recyclerView.setHasFixedSize(true);
        context = super.getContext();
        equiposAdapter = new EquiposAdapter(ClienteSession.getEquipos(), context, this);

        if (notCreated) {
            notCreated = false;


            reference.orderByChild("stock").startAt(1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Equipo> equipoList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        try {
                            Equipo equipo = children.getValue(Equipo.class);
                            equipo.setKey(children.getKey());
                            ClienteSession.addMarca(equipo.getMarca());
                            equipoList.add(equipo);

                        } catch (Exception e) {

                        }
                    }
                    ClienteSession.setEquipos(equipoList);
                    reloadList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            reloadList();
        }

        return view;
    }


    public void reloadList() {
        if (ClienteSession.isMarcaFiltred()) {
            labelMarca.setVisibility(View.VISIBLE);
            textMarca.setText(ClienteSession.getMarcaFiltro());
        } else {
            labelMarca.setVisibility(View.GONE);
        }
        if (ClienteSession.isDispositivoFiltred()) {
            labelDispositivo.setVisibility(View.VISIBLE);
            textDispositivo.setText(ClienteSession.getDispositivoFiltro());
        } else {
            labelDispositivo.setVisibility(View.GONE);
        }

        if (!ClienteSession.isDispositivoFiltred() && !ClienteSession.isMarcaFiltred()) {
            vistafiltros.setVisibility(View.GONE);
        } else {
            vistafiltros.setVisibility(View.VISIBLE);
        }


        equiposAdapter.setEquipos(ClienteSession.getEquipos());
        recyclerView.setAdapter(equiposAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ClienteEquipoActivity.class);
        intent.putExtra("equipo", ClienteSession.getEquipos().get(position));
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}