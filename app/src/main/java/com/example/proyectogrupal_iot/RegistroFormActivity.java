package com.example.proyectogrupal_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.proyectogrupal_iot.databinding.ActivityRegistroFormBinding;
import com.example.proyectogrupal_iot.entities.Cuenta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroFormActivity extends AppCompatActivity {

    ActivityRegistroFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.arrowBack.setOnClickListener(v -> finish());
        binding.buContinuar.setOnClickListener(v -> saverUser());

    }

    private void saverUser() {
        String codigo = binding.edCodigo.getText().toString().trim();
        String email = binding.edCorreo.getText().toString().trim();
        String pass1 = binding.edPassword1.getText().toString();
        String pass2 = binding.edPassword2.getText().toString();


        int radioButtonId = binding.raRol.getCheckedRadioButtonId();
        RadioButton radioButton = binding.raRol.findViewById(radioButtonId);


        boolean error = false;

        if (radioButton == null) {
            error=true;
            Toast.makeText(this, "No se ha seleccionado un rol", Toast.LENGTH_SHORT).show();
        }


        if (!pass1.equals(pass2)) {
            Toast.makeText(this, "Las contraseñas no concuerdan", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()){
            binding.edCodigo.setError("Llene este campo");
        }

        if (codigo.length() != 8) {
            binding.edCodigo.setError("Llene correctamente el campo");
            error = true;
        }
        if (email.isEmpty()) {
            error = true;
            binding.edCodigo.setError("Llene este campo");
        }
        if (pass1.isEmpty()) {
            error = true;
            binding.edPassword1.setError("Llene el campo");
            binding.edPassword2.setError("Llene el campo");
        }
        if (error) {
            return;
        }

        String rol = radioButton.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(t -> {
            if (t.isSuccessful()) {
                Cuenta cuenta = new Cuenta(rol, email, codigo);
                FirebaseDatabase.getInstance().getReference("cuenta").child(FirebaseAuth.getInstance().getUid()).setValue(cuenta);
                Toast.makeText(this, "Se ha creado la cuenta, verifica tu correo", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(this, "Tu contraseña es muy debil", Toast.LENGTH_SHORT).show();
            }
        );


    }


}