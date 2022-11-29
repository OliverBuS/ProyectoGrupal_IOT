package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectogrupal_iot.databinding.ActivityRecuperarCorreoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarCorreoActivity extends AppCompatActivity {

    ActivityRecuperarCorreoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarCorreoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buContinuar.setOnClickListener(v->{
            String email = binding.edMail.getText().toString().trim();
            if(email.isEmpty()){
                Toast.makeText(this, "Ingrese correo", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       Toast.makeText(this, "Se ha enviado el link de recuperación", Toast.LENGTH_SHORT).show();
                       finish();
                   } else{
                       Toast.makeText(this, "No se ha podido enviar el correo", Toast.LENGTH_SHORT).show();
                   }
                });
            }


        });
    }
}