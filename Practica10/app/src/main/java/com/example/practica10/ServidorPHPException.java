package com.example.practica10;

/**
 * Esta clase representa una excepción en el uso del acceso a un servidor remoto
 */

@SuppressWarnings("serial")
public class ServidorPHPException extends Exception
{
    /**
     * Constructor de la clase
     * @param message Mensaje
     */
    public ServidorPHPException(String message)
    {
        super(message);
    }
}
