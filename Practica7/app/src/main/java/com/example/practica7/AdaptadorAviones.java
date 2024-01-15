package com.example.practica7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorAviones extends RecyclerView.Adapter<AdaptadorAviones.AvionesViewHolder> {
    private ArrayList<Aviones> listaAviones;

    public AdaptadorAviones(ArrayList<Aviones> listaAviones) {
        this.listaAviones = listaAviones;
    }

    public void setListaAviones(ArrayList<Aviones> listaAviones) {
        this.listaAviones = listaAviones;
    }

    @NonNull
    @Override
    public AvionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.avion_item, parent, false);
        return new AvionesViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull AvionesViewHolder holder, int position) {
        holder.bindAviones(this.listaAviones.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listaAviones.size();
    }

    public static class AvionesViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreView;
        public ImageView imagenView;
        private Context context;

        public AvionesViewHolder(View view, Context context) {
            super(view);
            this.context = context;

            nombreView = view.findViewById(R.id.nombreView);
            imagenView = view.findViewById(R.id.imagenView);
        }

        public void bindAviones(Aviones avion) {
            nombreView.setText(avion.getNombre());
            Picasso.get().load(avion.getImageUrl()).into(imagenView);
        }
    }

}

