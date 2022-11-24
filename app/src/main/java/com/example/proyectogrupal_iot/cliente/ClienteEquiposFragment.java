package com.example.proyectogrupal_iot.cliente;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.proyectogrupal_iot.ClienteMainActivity;
import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.adapter.EquiposAdapter;
import com.example.proyectogrupal_iot.databinding.FragmentClienteEquiposBinding;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.save.ClienteSession;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ClienteEquiposFragment extends Fragment {





    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    FragmentClienteEquiposBinding binding;

    List<Equipo> equipoList = ClienteSession.getEquipos();
    RecyclerView recyclerView;


    private boolean notCreated = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentClienteEquiposBinding.inflate(getLayoutInflater());

        if(savedInstanceState !=null){
            EquiposAdapter equiposAdapter = new EquiposAdapter(equipoList,super.getContext());
            //binding.recycleViewer.setAdapter(equiposAdapter);
           //binding.recycleViewer.setLayoutManager(new LinearLayoutManager(super.getContext()));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("equipos" );
        View view = inflater.inflate(R.layout.fragment_cliente_equipos,container,false);
        recyclerView = view.findViewById(R.id.recycleViewer);
        recyclerView.setHasFixedSize(true);
        Context context = super.getContext();
        EquiposAdapter equiposAdapter = new EquiposAdapter(equipoList, context);

        if(notCreated) {
            notCreated=false;

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    equipoList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        Equipo equipo = children.getValue(Equipo.class);
                        equipoList.add(equipo);
                    }
                    Toast.makeText(context, String.valueOf(equipoList.size()), Toast.LENGTH_SHORT).show();

                    equiposAdapter.setEquipos(equipoList);
                    recyclerView.setAdapter(equiposAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else{
            equiposAdapter.setEquipos(equipoList);
            recyclerView.setAdapter(equiposAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}