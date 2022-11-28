package com.example.proyectogrupal_iot.ti;

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
import android.widget.Toast;

import com.example.proyectogrupal_iot.ClienteEquipoActivity;
import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.adapter.EquiposAdapter;
import com.example.proyectogrupal_iot.adapter.TIEquiposAdapter;
import com.example.proyectogrupal_iot.databinding.FragmentClienteEquiposBinding;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.interfaces.RecycleviewerInterface;
import com.example.proyectogrupal_iot.save.ClienteSession;
import com.example.proyectogrupal_iot.save.TISession;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TIEquiposFragment extends Fragment implements RecycleviewerInterface {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FragmentClienteEquiposBinding binding;
    RecyclerView recyclerView;
    TIEquiposAdapter tiequiposAdapter;
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
        View view = inflater.inflate(R.layout.fragment_ti_equipos, container, false);
        recyclerView = view.findViewById(R.id.recycleViewer);

        recyclerView.setHasFixedSize(true);
        context = super.getContext();
        tiequiposAdapter = new TIEquiposAdapter(TISession.getEquipos(), context, this);

        if (notCreated) {
            notCreated = false;
            reference.orderByChild("stock").startAt(1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Equipo> equipoList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        Equipo equipo = children.getValue(Equipo.class);
                        if (equipo != null) {
                            equipo.setKey(children.getKey());
                            TISession.addMarca(equipo.getMarca());
                        }
                        equipoList.add(equipo);
                    }
                    TISession.setEquipos(equipoList);
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
        tiequiposAdapter.setEquipos(TISession.getEquipos());
        recyclerView.setAdapter(tiequiposAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ClienteEquipoActivity.class);
        intent.putExtra("equipo", TISession.getEquipos().get(position));
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}