package co.edu.udea.compumovil.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Earthquakes> {

    private static final String TAG = "MainActivity";
    private static final String HOST = "http://api.geonames.org/";

    private ListView listView;
    private ArrayList<String> result;
    private ArrayAdapter<String> adapter;
    private ProgressDialog dialog;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = createApiService();

        listView = findViewById(R.id.listView);
        result = new ArrayList<>();
        dialog = new ProgressDialog(this);

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item);

        Call<Earthquakes> call = apiService.getEarthquakes("20", "-20", "-60", "-80", "aporter");
        call.enqueue(this);
    }

    private ApiService createApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HOST)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Override
    public void onResponse(Call<Earthquakes> call, Response<Earthquakes> response) {

        List<Earthquake> earthquakes = null;
        if (response.body() != null) {
            earthquakes = response.body().getEarthquakes();
        }

        if (earthquakes != null) {
            for (Earthquake earthQuake : earthquakes) {

                Log.d(TAG, "*earthQuakes: " + earthQuake.getMagnitude());
                result.add("Magnitude: " + earthQuake.getMagnitude()
                        + ", Lat :" + earthQuake.getLat()
                        + ", Lng :" + earthQuake.getLng());

                adapter.addAll(result);
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onFailure(Call<Earthquakes> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
    }
}
