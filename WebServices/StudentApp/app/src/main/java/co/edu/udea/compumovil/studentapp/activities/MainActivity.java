package co.edu.udea.compumovil.studentapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import org.json.JSONArray;

import co.edu.udea.compumovil.studentapp.R;
import co.edu.udea.compumovil.studentapp.adapters.AdapterRecyclerView;
import co.edu.udea.compumovil.studentapp.models.Student;
import co.edu.udea.compumovil.studentapp.webservices.ConfigURL;
import co.edu.udea.compumovil.studentapp.webservices.VolleySingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<Student> studentList;
    private AdapterRecyclerView adapter;
    private SwipeRefreshLayout refreshLayout;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Get refreshLayout
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(this);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerViewInit();
        requestStudents();
    }

    private void recyclerViewInit() {

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //specify an adapter
        adapter = new AdapterRecyclerView(getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    private void requestStudents() {

        Log.d(TAG, "request");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                ConfigURL.URL_STUDENTS,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, ""+response.toString());
                        Gson gson = new Gson();
                        Student[] data = gson.fromJson(response.toString(), Student[].class);
                        Log.d(TAG, ""+data.length);

                        studentList = new ArrayList<Student>(Arrays.asList(data));

                        if(studentList != null) {
                            /*for(int i=0; i<data.length; i++){
                                Log.d("*Students", "*Students: " + studentList.get(i).getFirstname());
                            }*/
                            adapter.updateList(studentList);
                        }else {
                            Log.d(TAG, "data is null ");
                        }
                        // Animation Stop
                        refreshLayout.setRefreshing(false);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error consultando información", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.getMessage());
                        // Animation Stop
                        refreshLayout.setRefreshing(false);
                    }
                }
        );
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh");
        requestStudents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
