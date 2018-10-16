package co.edu.udea.compumovil.communications;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static co.edu.udea.compumovil.communications.MainActivity.FRAGMENT_B_TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {

    public static final String TAG = "FragmentB";
    private TextView textView;
    private String name;

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    public static FragmentB newInstance(String name)
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        FragmentB fragment = new FragmentB();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            name = bundle.getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        textView = view.findViewById(R.id.message_text_view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        readBundle(getArguments());
        updateDisplay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    void setName(String name){
        this.name = name;
        updateDisplay();
    }

    void updateDisplay(){
        textView.setText("Name: " + name);
    }
}
