package com.example.practica7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AvionBD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Avion.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Aviones";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NOMBRE = "Nombre";
    private static final String COLUMN_URL = "URL";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOMBRE +
            " TEXT," + COLUMN_URL + " TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public AvionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    // Insertar Avion
    public void insertarAvion(String nombre, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOMBRE, nombre);
        contentValues.put(COLUMN_URL, url);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    // Obtener Aviones
    public ArrayList<Aviones> obtenerAviones() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_NOMBRE, COLUMN_URL};
        Cursor cursor = db.query(TABLE_NAME, projection, null, null,
                null, null, null);

        ArrayList<Aviones> avionesList = new ArrayList<>();

        if (cursor != null) {
            try {
                int nombreIndex = cursor.getColumnIndex(COLUMN_NOMBRE);
                int urlIndex = cursor.getColumnIndex(COLUMN_URL);

                while (cursor.moveToNext()) {
                    String nombre = (nombreIndex != -1) ? cursor.getString(nombreIndex) :
                            "Nombre no disponible";
                    String url = (urlIndex != -1) ? cursor.getString(urlIndex) :
                            "URL no disponible";

                    Aviones avion = new Aviones(nombre, url);
                    avionesList.add(avion);
                }
            } finally {
                cursor.close();
            }
        }

        db.close();

        return avionesList;
    }

    // Eliminar Todos los Aviones
    public void eliminarTodosLosAviones() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    // Cerrar Conexión
    public void cerrarConexion() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
