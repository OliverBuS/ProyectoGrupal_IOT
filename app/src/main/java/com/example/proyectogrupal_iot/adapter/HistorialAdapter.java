package com.example.proyectogrupal_iot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectogrupal_iot.R;
import com.example.proyectogrupal_iot.entities.Historial;
import com.example.proyectogrupal_iot.entities.Solicitud;
import com.example.proyectogrupal_iot.interfaces.RecycleviewerInterface;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder> {
    private List<Historial> historialList;
    private LayoutInflater inflater;
    private Context context;
    private final RecycleviewerInterface recycleviewerInterface;



    @Override
    public int getItemCount() {
        return historialList.size();
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_historial_vista, parent, false);
        return new HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {

        Historial historial = historialList.get(position);
        holder.historial =historial;

        ImageView imagen;
        TextView disp, desc,estado;
        Button button;
        CardView cardView;

        imagen = holder.itemView.findViewById(R.id.imEquipo);
        disp = holder.itemView.findViewById(R.id.textTipo);
        desc = holder.itemView.findViewById(R.id.textDesc);
        estado = holder.itemView.findViewById(R.id.teEstado);
        button = holder.itemView.findViewById(R.id.buMotivo);
        cardView = holder.itemView.findViewById(R.id.caEstado);

        disp.setText(historial.getTipo());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String msg="";
        if(!historial.getEstado().equals("rechazado")) {
            try {
                Calendar c = Calendar.getInstance();
                c.setTime( sdf.parse(historial.getFecha_respuesta()));
                c.add(Calendar.DATE, historial.getTiempo());
                msg += "Fecha prestamo: " + historial.getFecha_respuesta() +"\n"+
                        "Fecha devolución: " + sdf.format(c.getTime())+"\n"+
                        "Curso: " + historial.getCurso();

                Calendar c2 = Calendar.getInstance();
                if(c2.getTime().after(c.getTime())){
                    estado.setText("Devuelto");
                    estado.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_playlist_add_check_circle_32,0,0,0);
                    cardView.getBackground().setTint(Color.parseColor("#B39DDB"));
                } else {
                    estado.setText("Aprobado");
                }
                button.setVisibility(View.GONE);

            } catch (Exception e) {
                Log.d("HistorialAdapter", "onBindViewHolder: Error al parsear fecha");
                return;
            }
        } else{
            msg+= "Fecha de solicitud: "+historial.getFecha()+"\n"+
                    "Curso: " + historial.getFecha();
            estado.setText("Rechazado");
            cardView.getBackground().setTint(Color.parseColor("#EF5350"));
            estado.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_sms_failed_32,0,0,0);
        }



        desc.setText(msg);







        StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("equipos/"+historial.getImagen());
        Glide.with(context).load(imageRef).into(imagen);

    }


    public HistorialAdapter(List<Historial> itemList, Context context, RecycleviewerInterface recycleviewerInterface) {
        this.historialList = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recycleviewerInterface = recycleviewerInterface;
    }


    public void setHistorialList(List<Historial> items) {
        this.historialList= items;
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder {
        Historial historial;

        public HistorialViewHolder(@NonNull View view) {

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
