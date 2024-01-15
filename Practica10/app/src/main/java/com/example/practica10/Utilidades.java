package com.example.practica10;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

public final class Utilidades
{
    public static final int RESULTADO_OK = 1;
    public static final int RESULTADO_ERROR = 2;
    public static final int RESULTADO_ERROR_DESCONOCIDO = 3;

    public static final String URLSERVIDOR = "http://10.192.104.93/monumentos/";

    /**
     * Crea una URL válida con parámetros
     * @param url URL base
     * @param params Parámetros para la URL
     * @return URL formateada con sus parámetros
     */
    public static String buildURL(String url, HashMap<String, String> params)
    {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params != null)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toString();
    }
}
