package com.example.practica10;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Marker.OnMarkerClickListener {

    private ControladorMonumento controladorMonumento = new ControladorMonumento(this);
    private EditText editTextID;
    private Button buttonCompra;
    private TextView textNombreMonumento, textFecha, textDescripcion, textUbicacion, textViewLatitud, textViewLongitud, textMedia;
    private ImageView imagenMonumento;
    private WebView videoMain;
    private ScrollView scrollView;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCompra = findViewById(R.id.buttonCompra);
        buttonCompra.setOnClickListener(this);
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

        mapView = findViewById(R.id.smmap);
        Configuration.getInstance().setUserAgentValue("appIdMapPractice");
        MapController mapController = (MapController) mapView.getController();
        GeoPoint granadaPoint = new GeoPoint(37.1761617, -3.5970244,16);
        mapController.setCenter(granadaPoint);
        mapController.setZoom(16);

        // Llama al método para obtener todos los monumentos
        try {
            controladorMonumento.obtenerTodosMonumentos(new VolleyCallBack() {
                @Override
                public void onSuccess(Context context, ArrayList<Monumento> monumentos) {
                    // Verifica si la lista de monumentos no está vacía
                    if (!monumentos.isEmpty()) {
                        // Recorre la lista de monumentos y añadirlos al mapa
                        for (int i = 0; i < monumentos.size(); i++) {
                            monumentoPoint(monumentos.get(i));
                        }
                    }
                }
            });
        } catch (ServidorPHPException e) {
            throw new RuntimeException(e);
        }
    }

    // Crear el marcador
    private void monumentoPoint(Monumento monumento) {
        GeoPoint geoPoint = new GeoPoint(Double.parseDouble(monumento.getLatitud()),
                Double.parseDouble(monumento.getLongitud()));
        Marker marker = new Marker(mapView);
        marker.setPosition(geoPoint);
        mapView.getOverlays().add(marker);
        marker.setId(monumento.getId());
        marker.setOnMarkerClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }

    // Método para agregar un marcador en el mapa con un ID
    private void addMarker(MapView mapView, GeoPoint point, String markerID) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setSnippet(markerID);
        mapView.getOverlays().add(marker);

        // Configurar un evento de clic para el marcador
        marker.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker, MapView mapView) {
        // Obtener el ID asociado al marcador y cargar la información del monumento
        String MonumentoID = marker.getId();
        cargarInformacionMonumento(MonumentoID);
        return true;
    }

    // Método para cargar la información del monumento
    private void cargarInformacionMonumento(String MonumentoID) {
        try {
            controladorMonumento.obtenerMonumentoID(MonumentoID, new VolleyCallBack() {
                @Override
                public void onSuccess(Context context, ArrayList<Monumento> monumentos) {
                    // Verifica si la lista de monumentos no está vacía
                    if (!monumentos.isEmpty()) {
                        Monumento monumento = monumentos.get(0);

                        textNombreMonumento.setText(monumento.getNombre());
                        textFecha.setText("Fecha de construcción: " + monumento.getFecha());
                        Picasso.get().load(monumento.getImagen()).into(imagenMonumento);
                        textDescripcion.setText(monumento.getDescripcion());
                        textUbicacion.setText(monumento.getCiudad());
                        textViewLatitud.setText("Latitud: " + monumento.getLatitud());
                        textViewLongitud.setText("Longitud: " + monumento.getLongitud());

                        String html = monumento.getVideo();
                        WebSettings settings = videoMain.getSettings();
                        settings.setJavaScriptEnabled(true);
                        videoMain.loadData(html, "text/html", "UTF-8");

                        scrollView.setVisibility(View.VISIBLE);
                    } else {
                        Log.i("Advertencia", "Monumentos no encontrados");
                    }
                }
            });
        } catch (ServidorPHPException e) {
            throw new RuntimeException(e);
        }
    }
}
