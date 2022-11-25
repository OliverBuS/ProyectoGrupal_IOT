package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectogrupal_iot.databinding.ActivityMainBinding;
import com.example.proyectogrupal_iot.save.ReadSession;
import com.example.proyectogrupal_iot.save.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("cuenta");
        firebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();

    }

    private void setup() {
        binding.login.setOnClickListener(
                view -> {
                    if (!binding.edCorreo.getText().toString().isEmpty() && !binding.edPassword.getText().toString().isEmpty()) {
                        firebaseAuth.signInWithEmailAndPassword(binding.edCorreo.getText().toString(),
                                binding.edPassword.getText().toString()).addOnCompleteListener(it -> {
                            if (it.isSuccessful()) {
                                ref.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        ReadSession readSession = snapshot.getValue(ReadSession.class);

                                        if (readSession != null) {
                                            Session.rol = readSession.getRol();
                                            Session.codigo = readSession.getCodigo();
                                            if (Session.rol.equals("alumno")) {
                                                goHome();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Tu rol está pendiente", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            } else {
                                showAlert();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Llene los campos para poder iniciar sesión", Toast.LENGTH_SHORT).show();
                    }


                }
        );

        binding.registrar.setOnClickListener(view -> goRegistro());
        binding.recuperar.setOnClickListener(view -> goRecuperar());
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


    private void goRecuperar() {
        Intent intent = new Intent(this, RecuperarCorreoActivity.class);
        startActivity(intent);
    }

    private void goRegistro() {
        Intent intent = new Intent(this, RegistroFormActivity.class);
        startActivity(intent);
    }

    private void goHome(){
        Intent intent = new Intent(this, ClienteMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}