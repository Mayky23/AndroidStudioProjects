package com.example.practica5_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        // Crear datos para mostrar
        Aviones[] aviones = new Aviones[5];
        aviones[0]= new Aviones("Boeing 747","c", "Boeing");
        aviones[1]= new Aviones("Airbus A380","b", "Airbus");
        aviones[2]= new Aviones("Cessna 172", "e", "Cessna");
        aviones[3]= new Aviones("Boeing 787", "d", "Boeing");
        aviones[4]= new Aviones("Airbus A320", "a", "Airbus");

        // Crear el adaptador
        AdaptadorAviones adaptador = new AdaptadorAviones(aviones);

        // Configurar el RecyclerView
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adaptador);
    }
}

