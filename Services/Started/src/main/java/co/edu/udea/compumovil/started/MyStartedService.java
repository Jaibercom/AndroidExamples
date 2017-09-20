package co.edu.udea.compumovil.started;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyStartedService extends Service {

    private final String TAG = "MyStartedService";
    //private final int seconds = 1;
    private Counter thread;
    private int currentId;

    public MyStartedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "onStartCommand");

        currentId = startId;
        Log.d(TAG, "Service started");

        thread = new Counter(10, currentId);
        thread.start();

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //thread.setFlag(false);
        Toast.makeText(this, "Service Destroyed ID:"+ currentId, Toast.LENGTH_LONG).show();
        //Log.v(TAG, "onDestroy");
        Log.v(TAG, "Service Destroyed ID:"+ currentId);
    }




    public class Counter extends Thread {

        private int seconds;
        private Boolean flag;
        private int id;

        public Boolean getFlag() {
            return flag;
        }

        public void setFlag(Boolean flag) {
            this.flag = flag;
        }

        private final String TAG = "MyStartedService";

        public Counter() {
            seconds = 5;
            flag = true;
        }

        public Counter(int _seconds) {
            seconds = _seconds;
            flag = true;
        }

        public Counter(int _seconds, int _id) {
            seconds = _seconds;
            flag = true;
            id = _id;
        }

        @Override
        public void run() {

            for (int i = 0; i < seconds; i++) {

                if(flag) {
                    long endTime = System.currentTimeMillis() + 1000;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime -
                                        System.currentTimeMillis());
                            } catch (Exception e) {
                            }
                        }
                    }
                    Log.d(TAG, "ID: "+id + " counter: " + (i + 1));
                }
            }
            Log.d(TAG, "ID: "+id + " termino" );
            //stopSelf();
        }
    }

}
