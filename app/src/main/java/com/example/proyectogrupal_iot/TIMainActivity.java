package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyectogrupal_iot.cliente.ClienteEquiposFragment;
import com.example.proyectogrupal_iot.cliente.ClienteHistorialFragment;
import com.example.proyectogrupal_iot.cliente.ClienteSolicitudesFragment;
import com.example.proyectogrupal_iot.ti.TIEquiposFragment;
import com.example.proyectogrupal_iot.ti.TISolicitudesFragment;
import com.example.proyectogrupal_iot.databinding.*;
import com.example.proyectogrupal_iot.save.TISession;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TIMainActivity extends AppCompatActivity {

    ActivityTiMainBinding binding;
    MenuItem menuFiltro;
    TIEquiposFragment equiposFragment;
    TISolicitudesFragment solicitudesFragment;

    public static int navValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTiMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (equiposFragment == null) {
            equiposFragment = new TIEquiposFragment();
            solicitudesFragment = new TISolicitudesFragment();
        }
        switch (navValue) {
            case 0:
                replaceFragment(equiposFragment);
                break;
            case 1:
                replaceFragment(solicitudesFragment);
                break;
        }

        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.equipos){
                replaceFragment(equiposFragment);
                navValue = 0;
                mostrarFiltro(true);
            } else if ( id == R.id.solicitudes){
                replaceFragment(solicitudesFragment);
                navValue = 1;
                mostrarFiltro(false);
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
        inflater.inflate(R.menu.menu_ti_equipos, menu);
        menuFiltro = menu.findItem(R.id.filtro);
        if (navValue != 0) {
            menuFiltro.setVisible(false);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        equiposFragment = null;
        solicitudesFragment = null;
        super.onDestroy();
    }
}