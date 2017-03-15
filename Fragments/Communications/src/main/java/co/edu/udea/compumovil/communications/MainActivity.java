package co.edu.udea.compumovil.communications;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements FragmentA.OnFragmentButtonListener {

    public final String FRAGMENT_A_TAG = "tagA";
    public final String FRAGMENT_B_TAG = "tagB";
    public static final String KEY_NAME = "name";


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

        Log.d("MainActivity", "onFragmentClickButton, your name is:" + name);

        FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag(FRAGMENT_B_TAG);

        if (fragmentB == null) {
            Log.d("MainActivity", "Crear Fragment B");
            fragmentB = new FragmentB();
        }

        Bundle bundle = new Bundle();
        bundle.putString(KEY_NAME, name);
        fragmentB.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentB, FRAGMENT_B_TAG);
        ft.addToBackStack(null);
        ft.commit();


    }
}