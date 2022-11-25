package com.example.proyectogrupal_iot.adapter;

import android.content.Context;
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

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.SolicitudViewHolder> {
    private List<Solicitud> solicitudes;
    private LayoutInflater inflater;
    private Context context;
    private final RecycleviewerInterface recycleviewerInterface;



    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_solicitud_lista, parent, false);
        return new SolicitudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudViewHolder holder, int position) {

        Solicitud solicitud = solicitudes.get(position);
        holder.solicitud =solicitud;

        ImageView imagen;
        TextView disp, desc;

        imagen = holder.itemView.findViewById(R.id.imEquipo);
        disp = holder.itemView.findViewById(R.id.textTipo);
        desc = holder.itemView.findViewById(R.id.textDesc);

        String stringBuilder = "Fecha: " +
                solicitud.getFecha() +
                "\n" +
                "Curso: " +
                solicitud.getCurso() +
                "\n" +
                "Tiempo: " +
                solicitud.getTiempo() +
                " días";

        disp.setText(solicitud.getTipo());
        desc.setText(stringBuilder);

        StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("equipos/"+solicitud.getImagen());
        Glide.with(context).load(imageRef).into(imagen);

    }


    public SolicitudesAdapter(List<Solicitud> itemList, Context context, RecycleviewerInterface recycleviewerInterface) {
        this.solicitudes = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recycleviewerInterface = recycleviewerInterface;
    }


    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public class SolicitudViewHolder extends RecyclerView.ViewHolder {
        Solicitud solicitud;

        public SolicitudViewHolder(@NonNull View view) {

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
