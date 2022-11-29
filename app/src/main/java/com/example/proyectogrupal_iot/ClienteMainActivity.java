package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectogrupal_iot.cliente.ClienteEquiposFragment;
import com.example.proyectogrupal_iot.cliente.ClienteHistorialFragment;
import com.example.proyectogrupal_iot.cliente.ClienteSolicitudesFragment;
import com.example.proyectogrupal_iot.databinding.*;
import com.example.proyectogrupal_iot.save.ClienteSession;
import com.example.proyectogrupal_iot.save.Session;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;


public class ClienteMainActivity extends AppCompatActivity {

    ActivityClienteMainBinding binding;

    ClienteEquiposFragment equiposFragment;
    ClienteSolicitudesFragment solicitudesFragment;
    ClienteHistorialFragment historialFragment;

    public static int navValue = 0;

    MenuItem menuFiltro;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //PushNotifications.start(getApplicationContext(), "f59f82eb-bf93-4bd5-a98d-36c4dd416194");
        //PushNotifications.addDeviceInterest(FirebaseAuth.getInstance().getUid());
        drawerLayout = binding.drawerLayout;



        TextView accountInfo = binding.lateralNav.getHeaderView(0).findViewById(R.id.info);
        accountInfo.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail()+"\n"+Session.codigo);

        binding.lateralNav.setNavigationItemSelectedListener(item -> {
            if(item.getItemId()==R.id.logout){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
            }
            return false;
        });

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
                break;
            case 2:
                replaceFragment(historialFragment);
                break;

        }


        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.equipos) {
                replaceFragment(equiposFragment);
                navValue = 0;
                mostrarFiltro(true);
            } else if (id == R.id.solicitudes) {
                replaceFragment(solicitudesFragment);
                navValue = 1;
                mostrarFiltro(false);
            } else if (id == R.id.historial) {
                replaceFragment(historialFragment);
                mostrarFiltro(false);
                navValue = 2;
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
        if (item.getItemId() == R.id.filtroDispositivo) {
            mostrarOpcionesDispositivo();
            return true;
        } else if (item.getItemId() == R.id.filtroMarca) {
            mostrarOpcionesMarcas();
            return true;
        } else if(item.getItemId() == R.id.logout){
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.user){
            if(drawerLayout.isDrawerOpen(GravityCompat.END)){
                drawerLayout.closeDrawer(GravityCompat.END);
            } else{
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        equiposFragment = null;
        solicitudesFragment = null;
        historialFragment = null;
        super.onDestroy();
    }

    int option;

    private void mostrarOpcionesDispositivo() {
        String[] items = {"Laptop", "Tableta", "Celular", "Monitor", "Otro"};
        option = 0;

        new MaterialAlertDialogBuilder(this)
                .setTitle("Seleccione el tipo de dispositivo")
                .setNegativeButton("Cancelar", (dialog, which) -> {

                })
                .setPositiveButton("Buscar", (dialog, which) -> {
                    ClienteSession.setDispositivoFiltro(items[option]);
                    equiposFragment.reloadList();
                })
                .setSingleChoiceItems(items, 0, (dialog, which) -> {
                    option = which;
                })
                .setBackground(AppCompatResources.getDrawable(this, R.drawable.background_silver_rounded))
                .show();
    }

    private void mostrarOpcionesMarcas() {

        String[] items = ClienteSession.getMarcas().toArray(new String[0]);
        option = 0;

        new MaterialAlertDialogBuilder(this)
                .setTitle("Seleccione la marca")
                .setNegativeButton("Cancelar", (dialog, which) -> {
                })
                .setPositiveButton("Buscar", (dialog, which) -> {
                    ClienteSession.setMarcaFiltro(items[option]);
                    equiposFragment.reloadList();
                })
                .setSingleChoiceItems(items, 0, (dialog, which) -> {
                    option = which;
                })
                .setBackground(AppCompatResources.getDrawable(this, R.drawable.background_silver_rounded))
                .show();
    }


}