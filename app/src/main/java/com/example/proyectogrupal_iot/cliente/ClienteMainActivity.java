package com.example.proyectogrupal_iot.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.databinding.*;

public class ClienteMainActivity extends AppCompatActivity {


    ActivityClienteMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ClienteEquiposFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.equipos:
                    replaceFragment(new ClienteEquiposFragment());
                    break;
                case R.id.solicitudes:
                    replaceFragment(new ClienteSolicitudesFragment());
                    break;
                case R.id.historial:
                    replaceFragment(new ClienteHistorialFragment());
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


}