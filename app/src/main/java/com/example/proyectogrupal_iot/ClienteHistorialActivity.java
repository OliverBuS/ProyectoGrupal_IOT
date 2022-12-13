package com.example.proyectogrupal_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.proyectogrupal_iot.databinding.ActivityClienteHistorialBinding;
import com.example.proyectogrupal_iot.entities.Historial;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ClienteHistorialActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap mMap;
    ActivityClienteHistorialBinding binding;
    Historial historial;
    StorageReference stoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stoRef = FirebaseStorage.getInstance().getReference();
        historial = (Historial) getIntent().getSerializableExtra("historial");
        binding = ActivityClienteHistorialBinding.inflate(getLayoutInflater());

        binding.teEstado.setText(historial.getEstado());
        binding.teMotivo.setText(historial.getMotivo());
        binding.teCurso.setText(historial.getCurso());
        binding.teFechaEntrega.setText(historial.getFecha_respuesta());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        try {
            Calendar time= Calendar.getInstance();
            time.setTime(df.parse(historial.getFecha_respuesta()));;
            binding.teFechaDevoluci.setText(df.format(time.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.teProgramas.setText(historial.getProgramas());
        binding.teDispositivo.setText(historial.getTipo());

        Glide.with(this).load(stoRef.child(historial.getDni())).into(binding.imDNI);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        setContentView(binding.getRoot());

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(historial.getLat(),historial.getLon());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Lugar de recojo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}