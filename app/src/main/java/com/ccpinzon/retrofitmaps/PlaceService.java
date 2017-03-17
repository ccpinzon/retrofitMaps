package com.ccpinzon.retrofitmaps;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by cris_ on 16/03/2017.
 */

public interface PlaceService {
    @GET("testWebService")
    Call<List<Place>> getPlacesList();
}
