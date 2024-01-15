package com.example.practica7;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText urlEditText;
    private RecyclerView recyclerView;
    private AvionBD control;
    private ArrayList<Aviones> avionesList;
    private AdaptadorAviones adapter;
    private EditText nombreEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);
        urlEditText = findViewById(R.id.urlEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        Button buttonInsertar = findViewById(R.id.buttonInsertar);
        Button buttonBorrar = findViewById(R.id.buttonBorrar);

        control = new AvionBD(this);

        avionesList = new ArrayList<>();
        adapter = new AdaptadorAviones(avionesList);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        buttonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAvion();
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAviones();
            }
        });
    }

    private void agregarAvion() {
        String nombre = nombreEditText.getText().toString();
        String url = urlEditText.getText().toString();

        if (!nombre.isEmpty() && !url.isEmpty()) {
            Aviones nuevoAvion = new Aviones(nombre, url);
            control.insertarAvion(nuevoAvion.getNombre(), nuevoAvion.getImageUrl());

            avionesList = control.obtenerAviones();
            adapter.setListaAviones(avionesList);
            adapter.notifyDataSetChanged();
            nombreEditText.setText("");
            urlEditText.setText("");
        }
    }

    private void eliminarAviones() {
        control.eliminarTodosLosAviones();
        avionesList.clear();
        adapter.setListaAviones(avionesList);
        adapter.notifyDataSetChanged();
    }
}
