package com.example.practica7;


public class Aviones {
    private String nombre;
    private String imageUrl;

    public Aviones(String nombre, String imageUrl) {
        this.nombre = nombre;
        this.imageUrl = imageUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
