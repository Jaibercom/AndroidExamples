package co.edu.udea.compumovil.socket;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG-HttpGet";
    private TextView mTextView;
    private ProgressDialog dialog;
    private static final String HOST = "api.geonames.org";
    private static final String HTTP_GET_COMMAND = "GET /earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=aporter"
            + " HTTP/1.1"
            + "\n"
            + "Host: "
            + HOST
            + "\n"
            + "Connection: close" + "\n\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        dialog = new ProgressDialog(this);
    }

    public void onClick(View v) {
        new HttpGetTask().execute();
    }

    private class HttpGetTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait..");
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = "";

            try (
                    Socket socket = new Socket(HOST, 80);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
            ) {
                Log.d(TAG, HTTP_GET_COMMAND);
                pw.println(HTTP_GET_COMMAND);
                data = readStream(socket.getInputStream());

            } catch (UnknownHostException exception) {
                Log.e(TAG, "UnknownHostException " + exception.getMessage());
            } catch (IOException exception) {
                Log.e(TAG, "IOException " + exception.getMessage());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            Log.d(TAG, "Result: " + result);
            dialog.dismiss();
        }

        private String readStream(InputStream in) {
            StringBuilder data = new StringBuilder();
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            }
            return data.toString();
        }
    }
}
