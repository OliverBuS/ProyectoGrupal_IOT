package com.example.proyectogrupal_iot;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectogrupal_iot.databinding.ActivityFormDispositivoBinding;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormDispositivoActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText tipoOtroEquipo, marcaEquipo, caracteristicasEquipo, incluyeEquipo, stockEquipo;
    Spinner tipoEquipo;
    ActivityFormDispositivoBinding binding;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormDispositivoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tipoEquipo = findViewById(R.id.edTipo);
        tipoOtroEquipo = findViewById(R.id.edTipoOtro);
        marcaEquipo = findViewById(R.id.edMarca);
        caracteristicasEquipo = findViewById(R.id.edCaracteristicas);
        incluyeEquipo = findViewById(R.id.edIncluye);
        stockEquipo = findViewById(R.id.edStock);
        setupSubirImagen();

        binding.buAnadirDispositivo.setOnClickListener(v -> {
            AnadirDispositivo();
            startActivity(new Intent(FormDispositivoActivity.this, TIMainActivity.class));
        });
    }

    private void setupSubirImagen(){
        binding.subirEquipo.setOnClickListener(v->{
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
                            binding.subirEquipo.setImageBitmap(
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

    public void AnadirDispositivo(){
        String marcaEquipoStr = marcaEquipo.getText().toString().trim();
        String caracteristicasEquipoStr = caracteristicasEquipo.getText().toString().trim();
        String incluyeEquipoStr = incluyeEquipo.getText().toString().trim();
        String stockEquipoStr = stockEquipo.getText().toString().trim();
        int stockInt = Integer.parseInt(stockEquipoStr);
        String tipoEquipoStr = tipoEquipo.getSelectedItem().toString();
        String tipoOtroEquipoStr = tipoOtroEquipo.getText().toString().trim();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refequipos = ref.child("equipos");

        Equipo equipo = new Equipo(tipoEquipoStr, marcaEquipoStr, caracteristicasEquipoStr, incluyeEquipoStr, Arrays.asList("", ""), stockInt);

        if(tipoEquipoStr.equals("Otro")){
            binding.edTipoOtro.setVisibility(View.VISIBLE);
            equipo.setMarcaOtro(tipoOtroEquipoStr);
        }

        List<String> imagenesEquipoStr = Arrays.asList(imageUri.toString());

        String key = refequipos.push().getKey();
        refequipos.child(key).child("dispositivo").setValue(tipoEquipoStr);
        refequipos.child(key).child("marca").setValue(marcaEquipoStr);
        refequipos.child(key).child("incluye").setValue(incluyeEquipoStr);
        refequipos.child(key).child("stock").setValue(stockInt);
        refequipos.child(key).child("caracteristicas").setValue(caracteristicasEquipoStr);
        refequipos.child(key).child("imagenes").setValue(imagenesEquipoStr);

        Toast.makeText(FormDispositivoActivity.this, "Guardado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }


}