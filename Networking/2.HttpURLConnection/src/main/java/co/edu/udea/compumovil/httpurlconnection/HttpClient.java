package co.edu.udea.compumovil.httpurlconnection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Http Client class
 */
public class HttpClient {

    private static final String TAG = "HttpClient";
    private String url;

    HttpClient(String url) {
        this.url = url;
    }

    public String httpGet() {
        String data = "";
        HttpURLConnection httpUrlConnection = null;

        try {
            httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream in = new BufferedInputStream(httpUrlConnection.getInputStream());
            data = readStream(in);

        } catch (MalformedURLException exception) {
            Log.e(TAG, "MalformedURLException");
        } catch (IOException exception) {
            Log.e(TAG, "IOException");
        } finally {
            if (null != httpUrlConnection)
                httpUrlConnection.disconnect();
        }
        return data;
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
