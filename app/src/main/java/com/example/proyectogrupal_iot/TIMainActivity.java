package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.proyectogrupal_iot.ti.FormEquipoFragment;
import com.example.proyectogrupal_iot.ti.TIEquiposFragment;
import com.example.proyectogrupal_iot.ti.TIPerfilFragment;
import com.example.proyectogrupal_iot.ti.TISolicitudesFragment;
import com.example.proyectogrupal_iot.databinding.*;

import java.text.Normalizer;

public class TIMainActivity extends AppCompatActivity {

    ActivityTiMainBinding binding;
    MenuItem menuAddDel;
    TIEquiposFragment equiposFragment;
    TISolicitudesFragment solicitudesFragment;
    TIPerfilFragment perfilFragment;
    MenuItem v;

    public static int navValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTiMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (equiposFragment == null) {
            equiposFragment = new TIEquiposFragment();
            solicitudesFragment = new TISolicitudesFragment();
            perfilFragment = new TIPerfilFragment();
        }
        switch (navValue) {
            case 0:
                replaceFragment(equiposFragment);
                break;
            case 1:
                replaceFragment(solicitudesFragment);
                break;
            case 2:
                replaceFragment(perfilFragment);
                break;
        }

        binding.bottomNavTI.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.tiequipos){
                replaceFragment(equiposFragment);
                navValue = 0;
                mostrarAddDel(true);
            } else if ( id == R.id.tisolicitudes){
                replaceFragment(solicitudesFragment);
                navValue = 1;
                mostrarAddDel(false);
            }
            else if (id == R.id.tiperfil){
                replaceFragment(perfilFragment);
                navValue = 2;
                mostrarAddDel(false);
            }
            return true;
        });
    }

    private void mostrarAddDel(boolean valor) {
        if (menuAddDel != null) {
            menuAddDel.setVisible(valor);
        }
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_TI, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ti_equipos, menu);
        menuAddDel = menu.findItem(R.id.anadirEliminar);
        if (navValue != 0) {
            menuAddDel.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch(item.getItemId()) {
            case R.id.anadirDispositivo:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_TI,
                        FormEquipoFragment.newInstance("", "")).commit();
                break;
            case R.id.eliminarDispositivo:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_TI,
                        FormEquipoFragment.newInstance("", "")).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        equiposFragment = null;
        solicitudesFragment = null;
        perfilFragment = null;
        super.onDestroy();
    }
}