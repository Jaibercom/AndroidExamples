package co.edu.udea.compumovil.controlbutton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements View.OnClickListener{

    private TextView textView;
    private Button countButton;
    private Button clearButton;
    private int count = 0;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_a, container, false);

        clearButton = (Button) view.findViewById(R.id.clear_button);
        countButton = (Button) view.findViewById(R.id.count_button);
        clearButton.setOnClickListener(this);
        countButton.setOnClickListener(this);

        textView = (TextView) view.findViewById(R.id.text_view);
        updateUI();

        return view;
    }


    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.count_button:
                count++;
                updateUI();
                break;

            case R.id.clear_button:
                count = 0;
                updateUI();
                break;
        }
    }

    private void updateUI(){
        textView.setText(this.getString(R.string.text_view_msg) +" "+ count);
    }

}
