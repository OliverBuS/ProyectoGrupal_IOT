package com.example.proyectogrupal_iot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectogrupal_iot.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();


    }


    private void setup() {

        binding.login.setOnClickListener(
                view -> {
                    goHome();
                    return;
                    /*
                    if (!binding.edCorreo.getText().toString().isEmpty() && !binding.edPassword.getText().toString().isEmpty()) {
                        firebaseAuth.signInWithEmailAndPassword(binding.edCorreo.getText().toString(),
                                binding.edPassword.getText().toString()).addOnCompleteListener( it ->{
                           if(it.isSuccessful()){
                           } else{
                               showAlert();
                           }
                        });
                    }

                     */

                }
        );

        binding.registrar.setOnClickListener(view-> goRegistro());
        binding.recuperar.setOnClickListener(view-> goRecuperar());
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No se ha podido autenticar al usuario");
        builder.setTitle("Error");
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void goHome(){
        Intent intent = new Intent(this, ClienteMainActivity.class);
        startActivity(intent);
    }

    private void goRecuperar(){
        Intent intent = new Intent(this,RecuperarCorreoActivity.class);
        startActivity(intent);
    }

    private void goRegistro(){
        Intent intent = new Intent(this, RegistroFormActivity.class);
        startActivity(intent);
    }

}