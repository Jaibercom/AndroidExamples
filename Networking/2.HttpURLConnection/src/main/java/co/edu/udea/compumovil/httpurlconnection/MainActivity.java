package co.edu.udea.compumovil.httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog dialog;
    private static final String HOST = "http://api.geonames.org/";
    private String url;
    private String params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        dialog = new ProgressDialog(this);

        params = "?north=20&south=-20&east=-60&west=-80&username=aporter";
    }

    public void onClick(View v) {
        String request;

        switch (v.getId()) {
            case R.id.buttonJSON:
                request = "earthquakesJSON";
                break;

            case R.id.buttonXML:
                request = "earthquakes";
                break;

            default:
                request = "earthquakesJSON";
        }

        //url = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=aporter";
        url = HOST + request + params;
        HttpGetTask task = new HttpGetTask();
        task.execute();
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Start Progress Dialog (Message)
            dialog.setMessage("Please wait..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient client = new HttpClient(url);

            return client.httpGet();
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            dialog.dismiss();
        }
    }
}
