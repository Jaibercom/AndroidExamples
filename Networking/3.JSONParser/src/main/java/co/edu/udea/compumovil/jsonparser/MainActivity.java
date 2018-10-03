package co.edu.udea.compumovil.jsonparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ListView listView;
    private ArrayAdapter adapter;
    private static final String URL = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=aporter";
    private static final String TAG = "HttpGetTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<ArrayList>(getApplicationContext(), R.layout.list_item);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
    }

    public void onClick(View v) {

        new HttpGetTask().execute();
    }

    private class HttpGetTask extends AsyncTask<Void, Void, ArrayList> {

        private static final String LONGITUDE_TAG = "lng";
        private static final String LATITUDE_TAG = "lat";
        private static final String MAGNITUDE_TAG = "magnitude";
        private static final String EARTHQUAKE_TAG = "earthquakes";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Start Progress Dialog (Message)
            dialog.setMessage("Please wait..");
            dialog.show();
        }

        @Override
        protected ArrayList doInBackground(Void... params) {
            ArrayList<String> result = new ArrayList<>();
            HttpClient client = new HttpClient(URL);
            return parserData(client.httpGet());
        }

        @Override
        protected void onPostExecute(ArrayList dataList) {
            // Close progress dialog
            dialog.dismiss();
            adapter.addAll(dataList);
        }

        private ArrayList<String> parserData(String data){
            ArrayList<String> result = new ArrayList<>();

            //****************** Start Parse Response JSON Data *************
            try {
                //****** Creates a new JSONObject with name/value mappings from the JSON string. ********
                JSONObject responseObject = new JSONObject(data);
                JSONArray earthquakes = responseObject.getJSONArray(EARTHQUAKE_TAG);

                // Iterate over earthquakes list
                for (int i = 0; i < earthquakes.length(); i++) {
                    // Get single earthquake data - a Map
                    JSONObject earthquake = earthquakes.getJSONObject(i);

                    // Summarize earthquake data as a string and add it to result
                    result.add("Magnitude: "
                            + earthquake.get(MAGNITUDE_TAG) + ",  "
                            + LATITUDE_TAG + " :"
                            + earthquake.getString(LATITUDE_TAG) + ",  "
                            + LONGITUDE_TAG + " :"
                            + earthquake.get(LONGITUDE_TAG));
                }
                Log.v(TAG, "Result:" + result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
