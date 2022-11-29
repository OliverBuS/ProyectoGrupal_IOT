package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectogrupal_iot.databinding.ActivityClienteSolicitudBinding;
import com.example.proyectogrupal_iot.entities.Solicitud;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ClienteSolicitudActivity extends AppCompatActivity {

    ActivityClienteSolicitudBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteSolicitudBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Solicitud solicitud = (Solicitud) getIntent().getSerializableExtra("solicitud");

        binding.teTiempo.setText(String.valueOf(solicitud.getTiempo()));
        binding.edCurso.setText(solicitud.getCurso());
        binding.edProgramas.setText(solicitud.getProgramas());
        binding.edMotivo.setText(solicitud.getMotivo());
        if(solicitud.getOtros().isEmpty()){
            binding.campoOtros.setVisibility(View.GONE);
        } else{
            binding.edOtros.setText(solicitud.getOtros());
        }


        StorageReference ref= FirebaseStorage.getInstance().getReference().child(solicitud.getDni());
        Glide.with(this).load(ref).into(binding.imDNI);
        binding.teEquipo.setText(solicitud.getTipo());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}