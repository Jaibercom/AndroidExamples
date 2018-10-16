package co.edu.udea.compumovil.communications;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements FragmentA.OnFragmentButtonListener {

    public static final String TAG = "MainActivity";
    public static final String FRAGMENT_B_TAG = "TagB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentA fragmentA = new FragmentA();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, fragmentA, "FA");
        ft.commit();
    }


    @Override
    public void onFragmentClickButton(String name) {

        Log.d(TAG, "onFragmentClickButton, your name is:" + name);

        FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag(FRAGMENT_B_TAG);

        if (fragmentB == null) {
            Log.d(TAG, "Crear Fragment B");
            fragmentB = FragmentB.newInstance(name);
        }else {
            fragmentB.setName(name);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragmentB, FRAGMENT_B_TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}
