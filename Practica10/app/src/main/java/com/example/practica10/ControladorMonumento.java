package com.example.practica10;

import static com.example.practica10.Utilidades.RESULTADO_ERROR;
import static com.example.practica10.Utilidades.RESULTADO_ERROR_DESCONOCIDO;
import static com.example.practica10.Utilidades.RESULTADO_OK;
import static com.example.practica10.Utilidades.URLSERVIDOR;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorMonumento {

    private final String URLOBTENERMONUMENTOS = URLSERVIDOR + "obtenerTodosMonumentos.php";
    private final String URLOBTENERMONUMENTO = URLSERVIDOR + "obtenerMonumentoID.php";

    private Context contexto;
    private RecyclerView lista;

    public ControladorMonumento(Context contexto)
    {
        this.contexto = contexto;
    }

    /**
     * Obtiene todas los monumentos del servidor
     */
    public void obtenerTodosMonumentos(VolleyCallBack callBack) throws ServidorPHPException
    {
        ArrayList<Monumento> monumentos = new ArrayList<>();
        try
        {
            RequestQueue colavolley = Volley.newRequestQueue(contexto);
            String url = URLOBTENERMONUMENTOS;

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            try
                            {
                                if( response != null )
                                {
                                    int resultadoobtenido = response.getJSONObject(0).getInt("estado");

                                    switch(resultadoobtenido)
                                    {
                                        case RESULTADO_OK:
                                            JSONArray datosmonumentos = response.getJSONObject(0).getJSONArray("mensaje");
                                            for (int i = 0; i < datosmonumentos.length(); i++)
                                            {
                                                JSONObject per = datosmonumentos.getJSONObject(i);
                                                String id = per.getString("id");
                                                String nombre = per.getString("nombre");
                                                String descripcion = per.getString("descripcion");
                                                String fecha = per.getString("fecha");
                                                String latitud = per.getString("latitud");
                                                String longitud = per.getString("longitud");
                                                String ciudad = per.getString("ciudad");
                                                String visitable = per.getString("visitable");
                                                String precio = per.getString("precio");
                                                String moneda = per.getString("moneda");
                                                String video = per.getString("video");
                                                String imagen = per.getString("imagen");

                                                Monumento monumento = new Monumento(id, nombre, descripcion, fecha, latitud, longitud, ciudad, visitable, precio, moneda, video, imagen);
                                                monumentos.add(monumento);
                                                callBack.onSuccess(contexto, monumentos);
                                            }
                                            break;
                                        case RESULTADO_ERROR:
                                            throw new ServidorPHPException("Error, datos incorrectos.");
                                        case RESULTADO_ERROR_DESCONOCIDO:
                                            throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                    }
                                }
                                else
                                {
                                    throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                }
                            }
                            catch (JSONException | ServidorPHPException error)
                            {
                                System.out.println("Error -> " + error.toString());
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("Error -> " + error.toString());
                        }
                    }
            );

            // Agregamos la petición a la cola para que se ejecute
            colavolley.add(jsonObjectRequest);
        }
        catch(Exception error)
        {
            throw new ServidorPHPException(error.toString());
        }
    }

    /**
     * Obtiene un monumento del servidor según su ID
     */
    public void obtenerMonumentoID(String ID, VolleyCallBack callBack) throws ServidorPHPException
    {
        Monumento monumento = new Monumento();
        try
        {
            // Inicializo la cola de peticiones
            RequestQueue colavolley = Volley.newRequestQueue(contexto);

            // Declaro el array de los parámetros
            HashMap<String, String> parametros = new HashMap<>();
            // Meto los parámetros
            parametros.put("ID", ID);
            String urlfinal = Utilidades.buildURL(URLOBTENERMONUMENTO, parametros);
            Log.w("a", urlfinal);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    urlfinal,
                    null,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            try
                            {
                                if( response != null )
                                {
                                    int resultadoobtenido = response.getJSONObject(0).getInt("estado");
                                    //System.out.println("EL RESULTADO ES " + resultadoobtenido);

                                    switch(resultadoobtenido)
                                    {
                                        case RESULTADO_OK:
                                            JSONArray datosmonumento = response.getJSONObject(0).getJSONArray("mensaje");
                                            JSONObject monumentoJSON = datosmonumento.getJSONObject(0);

                                            monumento.setId(monumentoJSON.getString("id"));
                                            monumento.setNombre( monumentoJSON.getString("nombre"));
                                            monumento.setDescripcion(monumentoJSON.getString("descripcion"));
                                            monumento.setFecha(monumentoJSON.getString("fecha"));
                                            monumento.setLatitud(monumentoJSON.getString("latitud"));
                                            monumento.setLongitud(monumentoJSON.getString("longitud"));
                                            monumento.setCiudad(monumentoJSON.getString("ciudad"));
                                            monumento.setVisitable(monumentoJSON.getString("visitable"));
                                            monumento.setPrecio(monumentoJSON.getString("precio"));
                                            monumento.setMoneda(monumentoJSON.getString("moneda"));
                                            monumento.setVideo(monumentoJSON.getString("video"));
                                            monumento.setImagen(monumentoJSON.getString("imagen"));

                                            ArrayList<Monumento> monumentos = new ArrayList<>();
                                            monumentos.add(monumento);
                                            callBack.onSuccess(contexto, monumentos);
                                            break;
                                        case RESULTADO_ERROR:
                                            throw new ServidorPHPException("Error, datos incorrectos.");
                                        case RESULTADO_ERROR_DESCONOCIDO:
                                            throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                    }
                                }
                                else
                                {
                                    throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                }
                            }
                            catch (JSONException | ServidorPHPException e)
                            {
                                System.out.println("Error -> " + e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("Error -> " + error.toString());
                        }
                    }
            );

            // Agregamos la petición a la cola para que se ejecute
            colavolley.add(jsonObjectRequest);
        }
        catch(Exception e)
        {
            throw new ServidorPHPException("Error -> " + e.toString());
        }

    }
}
