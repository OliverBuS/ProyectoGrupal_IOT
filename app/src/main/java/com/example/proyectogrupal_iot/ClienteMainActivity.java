package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyectogrupal_iot.cliente.ClienteEquiposFragment;
import com.example.proyectogrupal_iot.cliente.ClienteHistorialFragment;
import com.example.proyectogrupal_iot.cliente.ClienteSolicitudesFragment;
import com.example.proyectogrupal_iot.databinding.*;
import com.example.proyectogrupal_iot.save.Session;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClienteMainActivity extends AppCompatActivity {


    ActivityClienteMainBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    static Fragment equiposFragment, solicitudesFragment, historialFragment;

    public static int navValue = 0;

    MenuItem menuFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this,Session.rol, Toast.LENGTH_SHORT).show();

        if (equiposFragment == null) {
            equiposFragment = new ClienteEquiposFragment();
            solicitudesFragment = new ClienteSolicitudesFragment();
            historialFragment = new ClienteHistorialFragment();
        }
        switch (navValue) {
            case 0:
                replaceFragment(equiposFragment);
                break;
            case 1:
                replaceFragment(solicitudesFragment);
                Toast.makeText(this, Session.codigo+" "+Session.rol, Toast.LENGTH_SHORT).show();

                break;
            case 2:
                replaceFragment(historialFragment);
                break;

        }


        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.equipos:
                    replaceFragment(equiposFragment);
                    navValue = 0;
                    mostrarFiltro(true);
                    break;
                case R.id.solicitudes:
                    replaceFragment(solicitudesFragment);
                    navValue = 1;
                    mostrarFiltro(false);
                    break;
                case R.id.historial:
                    replaceFragment(historialFragment);
                    mostrarFiltro(false);
                    navValue = 2;
                    break;

            }
            return true;
        });
    }

    private void mostrarFiltro(boolean valor) {
        if (menuFiltro != null) {
            menuFiltro.setVisible(valor);
        }
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cliente_equipos, menu);
        menuFiltro = menu.findItem(R.id.filtro);
        if (navValue != 0) {
            menuFiltro.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filtroDispositivo:
                return true;
            case R.id.filtroMarca:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        equiposFragment=null;
        solicitudesFragment=null;
        historialFragment=null;
        super.onDestroy();
    }
}