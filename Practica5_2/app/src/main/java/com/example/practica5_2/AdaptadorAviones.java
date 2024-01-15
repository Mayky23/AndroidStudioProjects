package com.example.practica5_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorAviones extends RecyclerView.Adapter<AdaptadorAviones.AvionViewHolder> {

    private Aviones[] lista_aviones;

    public AdaptadorAviones(Aviones[] lista_aviones) {
        this.lista_aviones = lista_aviones;
    }

    @NonNull
    @Override
    public AvionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avion_item, parent, false);
        return new AvionViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull AvionViewHolder holder, int position) {
        holder.bindAvion(this.lista_aviones[position]);
    }

    @Override
    public int getItemCount() {
        return this.lista_aviones.length;
    }

    public static class AvionViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public ImageView imageViewAvion;
        public TextView textViewFabricante;
        public Context context;

        public AvionViewHolder(View itemView, Context context) {
            super(itemView);
            this.context= context;

            textViewNombre = itemView.findViewById(R.id.textView9);
            imageViewAvion = itemView.findViewById(R.id.imageView3);
            textViewFabricante = itemView.findViewById(R.id.textView8);
        }

        public void bindAvion(Aviones avion) {
            textViewNombre.setText(avion.getNombre());
            // Reemplaza con el método adecuado para cargar la imagen
            // imageViewAvion.setImageResource(avion.getImagen());
            imageViewAvion.setImageResource(context.getResources().getIdentifier(avion.getImagen(),
                    "drawable", "com.example.practica5_2" ));
            textViewFabricante.setText(avion.getFabricante());

        }
    }
}
