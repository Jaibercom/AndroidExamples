package co.edu.udea.compumovil.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiService {

    @GET("/earthquakesJSON")
    Call<Earthquakes> getEarthquakes(
            @Query("north") String north,
            @Query("south") String south,
            @Query("east") String east,
            @Query("west") String west,
            @Query("username") String username);
}
