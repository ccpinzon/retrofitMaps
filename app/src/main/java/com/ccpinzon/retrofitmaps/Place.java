package com.ccpinzon.retrofitmaps;

/**
 * Created by cris_ on 16/03/2017.
 */

public class Place {

    private int id_estacion;
    private double latitud_estacion;
    private double longitud_estacion;
    private String nombre_estacion;
    private String descripcion_estacion;
    private String tel_fijo_estacion;

    public int getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(int id_estacion) {
        this.id_estacion = id_estacion;
    }

    public double getLatitud_estacion() {
        return latitud_estacion;
    }

    public void setLatitud_estacion(double latitud_estacion) {
        this.latitud_estacion = latitud_estacion;
    }

    public double getLongitud_estacion() {
        return longitud_estacion;
    }

    public void setLongitud_estacion(double longitud_estacion) {
        this.longitud_estacion = longitud_estacion;
    }

    public String getNombre_estacion() {
        return nombre_estacion;
    }

    public void setNombre_estacion(String nombre_estacion) {
        this.nombre_estacion = nombre_estacion;
    }

    public String getDescripcion_estacion() {
        return descripcion_estacion;
    }

    public void setDescripcion_estacion(String descripcion_estacion) {
        this.descripcion_estacion = descripcion_estacion;
    }

    public String getTel_fijo_estacion() {
        return tel_fijo_estacion;
    }

    public void setTel_fijo_estacion(String tel_fijo_estacion) {
        this.tel_fijo_estacion = tel_fijo_estacion;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id_estacion=" + id_estacion +
                ", latitud_estacion=" + latitud_estacion +
                ", longitud_estacion=" + longitud_estacion +
                ", nombre_estacion='" + nombre_estacion + '\'' +
                ", descripcion_estacion='" + descripcion_estacion + '\'' +
                ", tel_fijo_estacion='" + tel_fijo_estacion + '\'' +
                '}';
    }
}
