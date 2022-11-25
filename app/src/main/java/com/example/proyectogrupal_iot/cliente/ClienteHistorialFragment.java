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
import android.widget.Toast;

import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.adapter.HistorialAdapter;
import com.example.proyectogrupal_iot.adapter.SolicitudesAdapter;
import com.example.proyectogrupal_iot.databinding.FragmentClienteEquiposBinding;
import com.example.proyectogrupal_iot.entities.Historial;
import com.example.proyectogrupal_iot.entities.Solicitud;
import com.example.proyectogrupal_iot.interfaces.RecycleviewerInterface;
import com.example.proyectogrupal_iot.save.ClienteSession;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ClienteHistorialFragment extends Fragment implements RecycleviewerInterface {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView recyclerView;

    private boolean notCreated = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("historial/"+firebaseAuth.getUid());
        View view = inflater.inflate(R.layout.fragment_cliente_historial,container,false);
        recyclerView = view.findViewById(R.id.recycleViewer);
        recyclerView.setHasFixedSize(true);
        Context context = super.getContext();
        HistorialAdapter historialAdapter= new HistorialAdapter(ClienteSession.getHistorialLista(), context,this);

        if(notCreated) {
            notCreated=false;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Historial> historialList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        Historial historial = children.getValue(Historial.class);
                        if(historial!=null) {
                            historial.setKey(children.getKey());
                        }
                        historialList.add(historial);
                    }
                    ClienteSession.setHistorialLista(historialList);
                    if(!ClienteSession.checkfiltro()) {
                        historialAdapter.setHistorialList(ClienteSession.getHistorialLista());
                        recyclerView.setAdapter(historialAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else{
            historialAdapter.setHistorialList(ClienteSession.getHistorialLista());
            recyclerView.setAdapter(historialAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this.getContext(), "A desarrollar", Toast.LENGTH_SHORT).show();
    }
}