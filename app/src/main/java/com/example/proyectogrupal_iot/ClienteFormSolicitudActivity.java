package com.example.proyectogrupal_iot;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.proyectogrupal_iot.databinding.ActivityClienteFormSolicitudBinding;
import com.example.proyectogrupal_iot.entities.Solicitud;
import com.google.android.material.slider.Slider;

import java.io.IOException;

public class ClienteFormSolicitudActivity extends AppCompatActivity {


    ActivityClienteFormSolicitudBinding binding;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteFormSolicitudBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSwitchotros();
        setSliderChange();
        setupSubirImagen();
        binding.buEnviar.setOnClickListener(v->checkAndSave());

    }


    private void setSliderChange(){
        binding.sltiempo.addOnChangeListener((slider, value, fromUser) -> {
            binding.teTiempo.setText(String.valueOf(Math.round(value)));
        });
    }

    private void setSwitchotros(){
        binding.swOtros.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                binding.edOtros.setVisibility(View.VISIBLE);
            } else{
                binding.edOtros.setVisibility(View.GONE);
            }
        });
    }

    private void setupSubirImagen(){
        binding.subirDNI.setOnClickListener(v->{
            subirImagen();
        });
    }

    private void subirImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        galleryLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> galleryLauncher
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                            binding.subirDNI.setImageBitmap(
                                    selectedImageBitmap);

                            binding.subirIcon.setVisibility(View.GONE);
                            binding.subirTexto.setVisibility(View.GONE);
                            imageUri = selectedImageUri;
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });



    private void checkAndSave(){
        String motivo = binding.edMotivo.getText().toString();
        String curso = binding.edCurso.getText().toString();
        int tiempo = Math.round(binding.sltiempo.getValue());
        String programas = binding.edProgramas.getText().toString();
        boolean swOtros = binding.swOtros.isChecked();

        boolean existeError=false;
        String error ="No puede estar vacío";
        if(motivo.isEmpty()){
            binding.edMotivo.setError(error);
            existeError=true;
        }
        if(curso.isEmpty()){
            binding.edCurso.setError(error);
            existeError=true;
        }
        if(programas.isEmpty()){
            binding.edProgramas.setError(error);
            existeError=true;
        }
        if(imageUri==null){
            Toast.makeText(this, "Suba la imagen de su DNI", Toast.LENGTH_SHORT).show();
            existeError=true;

        }

        String otros ="";
        if(swOtros){
            otros = binding.edOtros.getText().toString();
            if(otros.isEmpty()){
                binding.edOtros.setError(error);
                existeError=true;
            }
        }
        if(existeError){
            return;
        }

        Solicitud solicitud = new Solicitud(motivo,curso,tiempo,programas);
        if(swOtros){
            solicitud.setOtros(otros);
        }
        Toast.makeText(this, "Se Envía", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}

