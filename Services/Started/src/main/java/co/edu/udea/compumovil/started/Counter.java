package co.edu.udea.compumovil.started;

import android.util.Log;

/**
 * Created by jaiber on 27/03/16.
 */
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
    }
}
