package com.example.proyectogrupal_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.proyectogrupal_iot.databinding.ActivityClienteFormSolicitudBinding;

public class ClienteFormSolicitudActivity extends AppCompatActivity {


    ActivityClienteFormSolicitudBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteFormSolicitudBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.swOtros.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                int altura = Math.round(this.getResources().getDisplayMetrics().density *80);
                binding.edOtros.setVisibility(View.VISIBLE);
            } else{
                binding.edOtros.setVisibility(View.GONE);
            }
        });
    }

}

