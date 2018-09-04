package co.edu.udea.compumovil.communications;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentButtonListener} interface
 * to handle interaction events.
 */
public class FragmentA extends Fragment implements View.OnClickListener{

    public final String TAG = "FragmentA";
    private OnFragmentButtonListener mListener;
    private EditText editText;
    private FloatingActionButton sendButton;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        editText = view.findViewById(R.id.name_edit_text);
        sendButton = view.findViewById(R.id.floating_button);
        sendButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        String name = editText.getText().toString();
        Log.d(TAG, "onClick, your name is: " + name);

        if(mListener != null){
            mListener.onFragmentClickButton(name);
            editText.setText("");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentButtonListener) {
            mListener = (OnFragmentButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentButtonListener {
        void onFragmentClickButton(String name);
    }

}
