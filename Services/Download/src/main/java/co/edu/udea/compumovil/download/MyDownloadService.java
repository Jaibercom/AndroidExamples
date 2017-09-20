package co.edu.udea.compumovil.download;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyDownloadService extends Service {
    private int counter = 0;
    private static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();
    private String TAG = "MyDownloadService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        doSomethingRepeatedly();

        try {
            new BackgroundTask().execute( new URL("http://www.google.com/imagen1.png"),
                                            new URL("http://www.google.com/imagen2.png"),
                                            new URL("http://www.google.com/imagen3.png"),
                                            new URL("http://www.google.com/imagen4.png"));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        Log.d(TAG, " Servicio Detenido");
        Toast.makeText(getBaseContext(), "Servicio Detenido",
                Toast.LENGTH_SHORT).show();
    }



    private void doSomethingRepeatedly() {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Log.d(TAG, String.valueOf(++counter) + " segundos");
            }

        }, 0, UPDATE_INTERVAL);
    }

    private int DownloadFile(URL url) {
        try {
            // Simulamos la descarga de un fichero
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return 100;
    }


    //Clase de Android para hacer tareas en background
    private class BackgroundTask extends AsyncTask<URL, Integer, Long> {

        private String TAG = "BackgroundTask";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Iniciando las descargas");
        }

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalKBytesDownloaded = 0;

            for (int i = 0; i < count; i++) {
                totalKBytesDownloaded += DownloadFile(urls[0]);
                // --Calculamos el porcentaje descargado y
                // --reportamos el progreso
                publishProgress((int) (((i + 1) / (float) count) * 100));
            }
            return totalKBytesDownloaded;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, String.valueOf(values[0]) + "% descargado");
            Toast.makeText(getBaseContext(), values[0] + "% descargado", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            Log.d(TAG, "Descargado "+ result + " KBytes");
            Toast.makeText(getBaseContext(),
                    "Descargado " + result + " KBytes", Toast.LENGTH_SHORT)
                    .show();
            stopSelf();
        }
    }

}
