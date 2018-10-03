package co.edu.udea.compumovil.volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog dialog;
    private final String TAG = "Volley";

    private static final String BASE_URL = "http://api.geonames.org";
    private static final String REQUEST = "/earthquakesJSON";
    private static final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=aporter";
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        dialog = new ProgressDialog(this);
    }

    public void onClick(View v) {
        url = BASE_URL + REQUEST + PARAMS;
        sendRequest();
    }

    private void sendRequest() {

        dialog.setMessage("Please wait..");
        dialog.show();
        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Method.GET,
                        url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                mTextView.setText(String.format("Response: %s", response.toString()));
                                dialog.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText(String.format("Error: %s", error.toString()));
                                dialog.dismiss();
                            }
                        }
                );
        requestQueue.add(jsObjRequest);
    }
}
