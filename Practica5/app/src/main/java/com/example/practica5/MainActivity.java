package com.example.practica5;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextCorreo = findViewById(R.id.cajaTextoCorreo);
        EditText editTextContraseña = findViewById(R.id.cajaTextoPass);
        Switch switchRecuerdame = findViewById(R.id.Recuerdame);
        Button buttonContinuar = findViewById(R.id.buttonContinuar);
        TextView resultText = findViewById(R.id.resultText);

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString().trim();
                String contraseña = editTextContraseña.getText().toString().trim();
                boolean recordar = switchRecuerdame.isChecked();

                if (correo.equals("correo@correo.com") && contraseña.equals("123")) {
                    if (recordar) {
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("Usuario y contraseña correctos, se guardarán para el siguiente acceso.");
                        resultText.setTextColor(Color.GREEN);
                    } else {
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("Usuario y contraseña correctos");
                        resultText.setTextColor(Color.GREEN);
                    }
                } else {
                    resultText.setVisibility(View.VISIBLE);
                    resultText.setText("Usuario o contraseña incorrectos");
                    resultText.setTextColor(Color.RED);
                }
            }
        });
    }
}
