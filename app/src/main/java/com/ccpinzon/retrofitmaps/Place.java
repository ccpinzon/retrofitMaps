package com.ccpinzon.retrofitmaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Place implements ClusterItem {

    private int id_estacion;
    private double latitud_estacion;
    private double longitud_estacion;
    private String nombre_estacion;
    private String tel_fijo_estacion;


    public Place(int id_estacion, double latitud_estacion, double longitud_estacion, String nombre_estacion, String tel_fijo_estacion) {
        this.id_estacion = id_estacion;
        this.latitud_estacion = latitud_estacion;
        this.longitud_estacion = longitud_estacion;
        this.nombre_estacion = nombre_estacion;
        this.tel_fijo_estacion = tel_fijo_estacion;
    }


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

    public String getTel_fijo_estacion() {
        return tel_fijo_estacion;
    }

    public void setTel_fijo_estacion(String tel_fijo_estacion) {
        this.tel_fijo_estacion = tel_fijo_estacion;
    }

    @Override
    public LatLng getPosition() {
        final LatLng mPosition;
        mPosition = new LatLng(latitud_estacion, longitud_estacion);
        return  mPosition;
    }


}
