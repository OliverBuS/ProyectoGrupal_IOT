package com.example.proyectogrupal_iot.ti;
import com.example.proyectogrupal_iot.R;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectogrupal_iot.entities.Equipo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class FormEquipoFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText tipoOtroEquipo, marcaEquipo, caracteristicasEquipo, incluyeEquipo, stockEquipo;
    Spinner tipoEquipo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormEquipoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormEquipoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormEquipoFragment newInstance(String param1, String param2) {
        FormEquipoFragment fragment = new FormEquipoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random random = new Random();
        firebaseDatabase = FirebaseDatabase.getInstance();
        View view = inflater.inflate(R.layout.fragment_form_equipo, container, false);
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refequipos = ref.child("equipos");
        // datos
        tipoEquipo = view.findViewById(R.id.edTipo);
        tipoOtroEquipo = view.findViewById(R.id.edTipoOtro);
        marcaEquipo = view.findViewById(R.id.edMarca);
        caracteristicasEquipo = view.findViewById(R.id.edCaracteristicas);
        incluyeEquipo = view.findViewById(R.id.edIncluye);
        stockEquipo = view.findViewById(R.id.edStock);

        View btnAnadirDev = view.findViewById(R.id.buAnadirDispositivo);
        btnAnadirDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fine = true;

                String marcaEquipoStr = marcaEquipo.getText().toString().trim();
                String caracteristicasEquipoStr = caracteristicasEquipo.getText().toString().trim();
                String incluyeEquipoStr = incluyeEquipo.getText().toString().trim();
                String stockEquipoStr = stockEquipo.getText().toString().trim();
                int stockInt = Integer.parseInt(stockEquipoStr);
                String tipoEquipoStr = tipoEquipo.getSelectedItem().toString();
                String tipoOtroEquipoStr = tipoOtroEquipo.getText().toString().trim();

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("dispositivo",tipoEquipoStr);
                userMap.put("marca", marcaEquipoStr);
                userMap.put("caracteristicas", caracteristicasEquipoStr);
                userMap.put("incluye", incluyeEquipoStr);
                userMap.put("stock", stockEquipoStr);

                refequipos.push().setValue(userMap).addOnSuccessListener(unused -> {
                    Toast.makeText(getContext(), "Equipo guardado correctamente", Toast.LENGTH_SHORT).show();
                });

                tipoOtroEquipo.setText("");
                marcaEquipo.setText("");
                caracteristicasEquipo.setText("");
                incluyeEquipo.setText("");
                stockEquipo.setText("");

                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frame_container_TI, TIEquiposFragment.newInstance("", ""))
                        .addToBackStack(null).commit();
            };
        });
        return view;
    }
}