package com.ccpinzon.retrofitmaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button _btnMaps;
    private Button _btnTest;
    private TextView _txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginComponets();





    }

    private void traerDatos() {
        Retrofit retrofit =  new Retrofit.Builder().baseUrl("http://webserver.mieds.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceService service = retrofit.create(PlaceService.class);

        Call<List<Place>> call = service.getPlacesList();

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {


                try {


                    List<Place> dataPlaces = response.body();
                    String data = "";
                    for (int i = 0; i < dataPlaces.size(); i++) {

                        if (i == 0) {
                            data = (dataPlaces.get(i).getId_estacion() + "\n" +
                                    dataPlaces.get(i).getNombre_estacion()) + "\n";
                        } else if (i == 1) {
                            data += (dataPlaces.get(i).getId_estacion() + "\n" +
                                    dataPlaces.get(i).getNombre_estacion() + "\n");
                        }

                    }
                    Toast.makeText(getApplication(),"SIRVE",Toast.LENGTH_SHORT).show();
                    _txtTest.setText(data);

                }catch (Exception e){
                    Log.d("ERROR","ERROR TRAYENDO DATOS");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void beginComponets() {
        _btnMaps = (Button) findViewById(R.id.btn_maps);
        _btnTest = (Button) findViewById(R.id.btn_test);
        _txtTest = (TextView) findViewById(R.id.txtTest);

        _btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                traerDatos();
            }
        });

        _btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(mapIntent);
            }
        });
    }
}
