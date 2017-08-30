package co.edu.udea.compumovil.dynamic_addreplace;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragmentA = new FragmentA();
        changeFragment(fragmentA);
    }

    public void onClick(View view){

        Snackbar snackbar = Snackbar.make(view, "onClick Button", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

        Fragment fragmentB = new FragmentB();
        changeFragment(fragmentB);
    }

    public void changeFragment(Fragment fragment){

        // Create transaction
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        // Create new fragment
        //Fragment fragmentA = new FragmentA();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.fragment_container, fragment);

        // add the transaction to the back stack (optional)
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
