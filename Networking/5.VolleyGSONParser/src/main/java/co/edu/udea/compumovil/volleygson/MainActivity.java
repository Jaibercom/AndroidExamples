package co.edu.udea.compumovil.volleygson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "VolleyGSON";
    private ListView listView;
    private ArrayList<String> result;
    private ArrayAdapter<String> adapter;
    private ProgressDialog dialog;

    private static final String HOST = "http://api.geonames.org/";
    private static final String REQUEST = "earthquakesJSON";
    private static final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=aporter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        result = new ArrayList<>();
        dialog = new ProgressDialog(this);

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item);

        sendRequest(HOST + REQUEST + PARAMS);
    }

    private void sendRequest(String url) {

        dialog.setMessage("Please wait..");
        dialog.show();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Method.GET,
                        url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                dialog.dismiss();
                                parserData(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error: " + error.toString());
                                dialog.dismiss();
                            }
                        }
                );
        queue.add(jsObjRequest);
    }

    private void parserData(String data) {

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(data);
        JsonArray jsonArr = jo.getAsJsonArray("earthquakes");

        Gson gson = new Gson();
        EarthQuake[] earthQuakes = gson.fromJson(jsonArr, EarthQuake[].class);

        if (earthQuakes != null) {

            for (EarthQuake earthQuake : earthQuakes) {
                Log.d(TAG, "*earthQuakes: " + earthQuake.getMagnitude());
                result.add("Magnitude: " + earthQuake.getMagnitude()
                        + ", Lat :" + earthQuake.getLat()
                        + ", Lng :" + earthQuake.getLng());
            }

            adapter.addAll(result);
            listView.setAdapter(adapter);
        } else {
            Log.d(TAG, "earthQuakes is null ");
        }
    }
}
