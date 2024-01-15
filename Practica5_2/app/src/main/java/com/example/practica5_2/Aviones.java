package com.example.practica5_2;

// Esta clase representa un modelo de datos para aviones
public class Aviones {
    // Atributos para almacenar información sobre el avión
    private String nombre;
    private String imagen;
    private String fabricante;

    public Aviones(String nombre, String imagen, String fabricante) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.fabricante = fabricante;

    }
    // Métodos getter y setter para cada atributo
    public String getNombre() {
        return nombre;
    }
    public String getImagen() {
        return imagen;
    }
    public String getFabricante() {
        return fabricante;
    }




}

