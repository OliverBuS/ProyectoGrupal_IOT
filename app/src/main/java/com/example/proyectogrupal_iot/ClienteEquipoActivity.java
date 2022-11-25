package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.proyectogrupal_iot.databinding.ActivityClienteEquipoBinding;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ClienteEquipoActivity extends AppCompatActivity {

    private Equipo equipo;

    ActivityClienteEquipoBinding binding;
    FirebaseStorage firebaseStorage;
    StorageReference reference;

    List<String> linkImagenes = new ArrayList<>();

    ObservableInteger obsInt = new ObservableInteger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteEquipoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseStorage = FirebaseStorage.getInstance();
        reference = firebaseStorage.getReference("equipos");

        equipo = (Equipo) getIntent().getSerializableExtra("equipo");
        List<SlideModel> slideModels = new ArrayList<>();

        obsInt.set(slideModels.size());

        for(String i: equipo.getImagenes()){
            reference.child(i).getDownloadUrl().addOnSuccessListener(uri -> {
                linkImagenes.add(uri.toString());
                slideModels.add(new SlideModel(uri.toString(),ScaleTypes.FIT));
                obsInt.set(slideModels.size());
            });
        }

        obsInt.setOnIntegerChangeListener(newValue -> {
            if(newValue == equipo.getImagenes().size()){
                binding.slider.setImageList(slideModels);
            }
        });

        binding.teCaracteristicas.setText(equipo.getCaracteristicas());
        binding.teDispositivo.setText(equipo.getDispositivo());
        binding.teIncluye.setText(equipo.getIncluye());
        binding.teMarca.setText(equipo.getMarca());


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.solicitar){
            Intent intent = new Intent(this,ClienteFormSolicitudActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_solicitar, menu);
        return true;
    }

    public interface OnIntegerChangeListener
    {
        public void onIntegerChanged(int newValue);
    }

    public class ObservableInteger
    {
        private OnIntegerChangeListener listener;

        private int value;

        public void setOnIntegerChangeListener(OnIntegerChangeListener listener)
        {
            this.listener = listener;
        }

        public int get()
        {
            return value;
        }

        public void set(int value)
        {
            this.value = value;

            if(listener != null)
            {
                listener.onIntegerChanged(value);
            }
        }
    }

}