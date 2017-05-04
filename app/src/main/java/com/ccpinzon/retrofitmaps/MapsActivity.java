package com.ccpinzon.retrofitmaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //private List<Place> placesLista;
    private ClusterManager<Place> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        traerDatos();



    }

    private void traerDatos(){

        Retrofit retrofit =  new Retrofit.Builder().baseUrl("http://webserver.mieds.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceService service = retrofit.create(PlaceService.class);

        Call<List<Place>> call = service.getPlacesList();

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                try {
                    mClusterManager = new ClusterManager<Place>(getApplication(),mMap);
                    mMap.setOnCameraIdleListener(mClusterManager);
                    mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Place>() {
                        @Override
                        public boolean onClusterItemClick(Place place) {
                            Toast.makeText(getApplication(),"prueba" + place.getNombre_estacion(),Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });

                    List<Place> dataPlaces = response.body();
                    mClusterManager.addItems(dataPlaces);

                    //Llenar el mapa de marcadores
//                    for (Place place : dataPlaces) {
//                        LatLng pos = new LatLng(place.getLatitud_estacion(), place.getLongitud_estacion());
//                        mMap.addMarker(new MarkerOptions().position(pos)
//                                .title(place.getNombre_estacion())
//                                .snippet(String.valueOf(place.getId_estacion())));
//                    }

                    //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();



                }catch (Exception e){
                    Log.d("ERROR","ERROR TRAYENDO DATOS");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {

            }
        });

    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng marquer = new LatLng(5.7503972, -73.4339149);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marquer,14));
    }
}
