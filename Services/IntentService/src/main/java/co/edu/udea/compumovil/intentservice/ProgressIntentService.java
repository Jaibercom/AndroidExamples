package co.edu.udea.compumovil.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class ProgressIntentService extends IntentService {

    private static final String TAG = ProgressIntentService.class.getSimpleName();

    //Constructor
    public ProgressIntentService() {
        super("ProgressIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constants.ACTION_RUN_ISERVICE.equals(action)) {
                handleActionRun();
            }
        }
    }

    /**
     * Maneja la acción de ejecución del servicio
     */
    private void handleActionRun() {
        try {
            // Se construye la notificación
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("Servicio en segundo plano")
                    .setContentText("Procesando...");

            // Bucle de simulación
            for (int i = 1; i <= 15; i++) {

                Log.d(TAG, i + ""); // Logueo

                // Poner en primer plano
                builder.setProgress(15, i, false);
                startForeground(1, builder.build());


                // Retardo de 1 segundo en la iteración
                Thread.sleep(1000);
            }
            // Quitar de primer plano
            stopForeground(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
