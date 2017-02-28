package co.edu.udea.compumovil.dynamic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Fragment fragmentA;
    private Fragment fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragmentA, "TAG_A");
        transaction.commit();

        Log.d(TAG, "onCreate");

    }

    public void onClick(View view) {

        Log.d(TAG, "onClick");

        // Create new fragment and transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Capture the fragment from the activity layout
        FragmentA fragment = (FragmentA) getSupportFragmentManager().findFragmentByTag("TAG_A");

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        if (fragment != null) {
            Log.d(TAG, "Mostrar el Fragment B");
            transaction.replace(R.id.fragment_container, fragmentB, "TAG_B");
        } else {
            Log.d(TAG, "Mostrar el Fragment A");
            transaction.replace(R.id.fragment_container, fragmentA, "TAG_A");
        }
        //transaction.addToBackStack(null);         //Para que sirve ??

        // Commit the transaction
        transaction.commit();
    }
}
