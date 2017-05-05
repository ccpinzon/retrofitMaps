package com.ccpinzon.retrofitmaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<Place>, ClusterManager.OnClusterInfoWindowClickListener<Place>, ClusterManager.OnClusterItemClickListener<Place>, ClusterManager.OnClusterItemInfoWindowClickListener<Place> {

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

                    mClusterManager.setRenderer(new ClusterRendered(getApplicationContext(), mMap, mClusterManager));

                    mMap.setOnMarkerClickListener(mClusterManager);

                    List<Place> dataPlaces = response.body();
                    mClusterManager.addItems(dataPlaces);

                    mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

                    mMap.setOnInfoWindowClickListener(mClusterManager); //added

                    mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Toast.makeText(getApplicationContext(), "infla", Toast.LENGTH_SHORT).show();
                        }
                    });

                    mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            View mostrar = getLayoutInflater().inflate(R.layout.layout_mostrar, null);

                            TextView teTitulo = (TextView) mostrar.findViewById(R.id.teNombreEstacion);
                            TextView teSnipet = (TextView) mostrar.findViewById(R.id.teSnipet);

                            teTitulo.setText(marker.getTitle());
                            teSnipet.setText(marker.getSnippet());

                            return mostrar;
                        }
                    });
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng marquer = new LatLng(5.7503972, -73.4339149);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marquer,8));
    }

    @Override
    public boolean onClusterClick(Cluster cluster) {
        return false;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<Place> cluster) {

    }

    @Override
    public boolean onClusterItemClick(Place place) {

        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Place place) {

    }
}
