package com.example.proyectogrupal_iot.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.entities.Solicitud;
import com.example.proyectogrupal_iot.interfaces.RecycleviewerInterface;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class TIEquiposAdapter extends RecyclerView.Adapter<TIEquiposAdapter.TIEquipoViewHolder> {
    private List<Equipo> equipos;
    private LayoutInflater inflater;
    private Context context;
    private final RecycleviewerInterface recycleviewerInterface;

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    @NonNull
    @Override
    public TIEquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_equipos_lista_ti, parent, false);
        return new TIEquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TIEquiposAdapter.TIEquipoViewHolder holder, int position) {

        Equipo equipo = equipos.get(position);
        holder.equipo =equipo;

        ImageView imagen;
        TextView disp, marca, desc;

        imagen = holder.itemView.findViewById(R.id.imEquipo);
        disp = holder.itemView.findViewById(R.id.textTipo);
        marca = holder.itemView.findViewById(R.id.textMarca);
        desc = holder.itemView.findViewById(R.id.textDesc);

        disp.setText(equipo.getDispositivo());
        marca.setText(equipo.getMarca());
        desc.setText(equipo.getCaracteristicas());

        StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("equipos/"+equipo.getImagenPrincipal());
        Glide.with(context).load(imageRef).into(imagen);


    }


    public TIEquiposAdapter(List<Equipo> itemList, Context context, RecycleviewerInterface recycleviewerInterface) {
        this.equipos = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recycleviewerInterface = recycleviewerInterface;
    }


    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public class TIEquipoViewHolder extends RecyclerView.ViewHolder {
        Equipo equipo;

        public TIEquipoViewHolder(@NonNull View view) {

            super(view);

            view.setOnClickListener(v->{
                if(recycleviewerInterface !=null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        recycleviewerInterface.onItemClick(pos);
                    }
                }
            });
        }

    }
}
