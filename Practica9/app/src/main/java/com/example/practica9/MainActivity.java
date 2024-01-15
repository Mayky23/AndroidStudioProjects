package com.example.practica9;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ControladorMonumento controladorMonumento = new ControladorMonumento(this);
    private EditText editTextID;
    private Button buttonBuscar, botonCompra;
    private TextView textNombreMonumento, textFecha, textDescripcion, textUbicacion, textViewLatitud, textViewLongitud, textMedia;
    private ImageView imagenMonumento;
    private WebView videoMain;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextID = findViewById(R.id.EditText_ID);
        buttonBuscar = findViewById(R.id.buttonBuscar);

        textNombreMonumento = findViewById(R.id.textNombreMonumento);
        textFecha = findViewById(R.id.textFecha);
        imagenMonumento = findViewById(R.id.imagenMonumento);
        textDescripcion = findViewById(R.id.textDescripcion);
        textUbicacion = findViewById(R.id.textUbicacion);
        textViewLatitud = findViewById(R.id.textViewLatitud);
        textViewLongitud = findViewById(R.id.textViewLongitud);
        textMedia = findViewById(R.id.textMedia);
        videoMain = findViewById(R.id.viewVideo);

        scrollView = findViewById(R.id.scrollView);


        buttonBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String MonumentoID = editTextID.getText().toString();

        if (view.getId() == R.id.buttonBuscar){
            try {
                controladorMonumento.obtenerMonumentoID(MonumentoID, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Context context, ArrayList<Monumento> monumentos) {
                        Monumento monumento = monumentos.get(0);

                        textNombreMonumento.setText(monumento.getNombre());
                        textFecha.setText("Fecha de construccion: " + monumento.getFecha());
                        Picasso.get().load(monumento.getImagen()).into(imagenMonumento);
                        textDescripcion.setText(monumento.getDescripcion());
                        textUbicacion.setText(monumento.getCiudad());
                        textViewLatitud.setText("Latitud: "+monumento.getLatitud());
                        textViewLongitud.setText("Longitud: "+monumento.getLongitud());

                        String html = monumento.getVideo();
                        WebSettings settings = videoMain.getSettings();
                        settings.setJavaScriptEnabled(true);
                        videoMain.loadData(html, "text/html", "UTF-8");

                        scrollView.setVisibility(View.VISIBLE);
                    }
                });
            }catch (ServidorPHPException e){

            }

        }
    }
}
