package com.example.proyectogrupal_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.cliente.ClienteEquiposFragment;
import com.example.proyectogrupal_iot.cliente.ClienteHistorialFragment;
import com.example.proyectogrupal_iot.cliente.ClienteSolicitudesFragment;
import com.example.proyectogrupal_iot.databinding.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClienteMainActivity extends AppCompatActivity {


    ActivityClienteMainBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    Fragment equiposFragment, solicitudesFragment,historialFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        equiposFragment = new ClienteEquiposFragment();
        replaceFragment(equiposFragment);
        solicitudesFragment = new ClienteSolicitudesFragment();
        historialFragment = new ClienteHistorialFragment();



        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.equipos:
                    replaceFragment(equiposFragment);
                    break;  
                case R.id.solicitudes:
                    replaceFragment(solicitudesFragment);
                    break;
                case R.id.historial:
                    replaceFragment(historialFragment);
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cliente_equipos, menu);
        return true;
    }
}