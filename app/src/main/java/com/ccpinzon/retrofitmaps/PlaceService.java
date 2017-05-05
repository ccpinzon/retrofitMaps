package com.ccpinzon.retrofitmaps;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

interface PlaceService {
    @GET("estaciones.php")
    Call<List<Place>> getPlacesList();
}
