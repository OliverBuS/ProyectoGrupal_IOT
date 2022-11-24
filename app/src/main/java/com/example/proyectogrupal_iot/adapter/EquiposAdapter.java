package com.example.proyectogrupal_iot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.entities.Equipo;

import java.util.List;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.EquipoViewHolder> {
    private List<Equipo> equipos;
    private LayoutInflater inflater;
    private Context context;


    @Override
    public int getItemCount() {
        return equipos.size();
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_equipos_lista, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {

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


    }


    public EquiposAdapter(List<Equipo> itemList, Context context) {
        this.equipos = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder {
        Equipo equipo;

        public EquipoViewHolder(@NonNull View view) {
            super(view);
        }

    }
}
