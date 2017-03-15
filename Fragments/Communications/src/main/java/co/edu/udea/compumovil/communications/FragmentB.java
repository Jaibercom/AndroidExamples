package co.edu.udea.compumovil.communications;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {

    private TextView textView;
    private String name;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        textView = (TextView) view.findViewById(R.id.message_text_view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        name = bundle.getString(MainActivity.KEY_NAME);

        updateDisplay();
    }

    void updateDisplay(){
        textView.setText("Name: " + name);
    }

}
