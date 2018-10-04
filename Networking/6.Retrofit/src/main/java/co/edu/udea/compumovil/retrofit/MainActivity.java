package co.edu.udea.compumovil.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<ArrayList<EarthQuake>> {

    private static final String BASE = "https://samples.openweathermap.org/data/2.5/weather/";
    private static final String HOST = "http://api.geonames.org/";
    private static final String REQUEST = "earthquakesJSON";
    private static final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=aporter";

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = createApiService();

        Call<ArrayList<EarthQuake>> call = apiService.getEarthquakes("20", "-20", "-60", "-80", "aporter");
        call.enqueue(this);

    }

    private ApiService createApiService() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(HOST)
                .client(okHttpClient)
//                .baseUrl(BASE)
                .build();

        return retrofit.create(ApiService.class);
    }


    @Override
    public void onResponse(Call<ArrayList<EarthQuake>> call, Response<ArrayList<EarthQuake>> response) {


        Log.d("TAG", "AQ: " + response.body());
//        for (EarthQuake earthQuake : response.body()) {
//            Log.d("TAG", "AQ: " + earthQuake.getDatetime());
//        }

    }

    @Override
    public void onFailure(Call<ArrayList<EarthQuake>> call, Throwable t) {
        Log.d("TAG", "Failure: " + t.getMessage());
    }
}
