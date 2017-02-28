package co.edu.udea.compumovil.listfragment;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private String [] planetsList;
    private final String TAG = "MyListFragment";

    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create adapter with array-string form string values
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(
//                getActivity(),
//                R.array.Planets,
//                android.R.layout.simple_list_item_1);

        //get array-string from resources
        planetsList = getResources().getStringArray(R.array.Planets);
        //Create adapter with array-string form a normal array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, planetsList);


        //Set adapter
        setListAdapter(adapter);
        //Set event onClick
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Get planet name position
        String planetName = planetsList[position];

        //Show message in toast
        Toast.makeText(getActivity(), "This planet is: " + planetName, Toast.LENGTH_SHORT).show();
        Log.d(TAG,"This planet is: " + planetName );
    }
}
