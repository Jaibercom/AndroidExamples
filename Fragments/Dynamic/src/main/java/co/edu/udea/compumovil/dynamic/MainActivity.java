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
    private Boolean isFragmentA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragment_container, fragmentA);
        //transaction.commit();

        isFragmentA = false;
    }

    public void onClick(View view) {

        Log.d(TAG, "onClick");

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        if (isFragmentA == true) {
            displayFragmentB();
            isFragmentA = false;
        } else {
            displayFragmentA();
            isFragmentA = true;
        }

    }

    // Replace the switch method
    protected void displayFragmentA() {

        Log.d(TAG, "Mostrar el Fragment A");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragmentA.isAdded()) { // if the fragment is already in container
            ft.show(fragmentA);
        } else { // fragment needs to be added to frame container
            ft.add(R.id.fragment_container, fragmentA);
        }
        // Hide fragment B
        if (fragmentB.isAdded()) {
            ft.hide(fragmentB);
        }
        // Commit changes
        ft.commit();
    }

    // Replace the switch method
    protected void displayFragmentB() {

        Log.d(TAG, "Mostrar el Fragment B");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragmentB.isAdded()) { // if the fragment is already in container
            ft.show(fragmentB);
        } else { // fragment needs to be added to frame container
            ft.add(R.id.fragment_container, fragmentB);
        }
        // Hide fragment A
        if (fragmentA.isAdded()) {
            ft.hide(fragmentA);
        }
        // Commit changes
        ft.commit();
    }


}
