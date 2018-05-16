package com.example.mike.mikeleonad340;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cams extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private List<CamsDetail> cameraList;
    public String id;
    public String description;
    public String type;
    public String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_cams);

        context = getApplicationContext();
        cameraList = new ArrayList<>();
        relativeLayout = findViewById(R.id.relativelayout1);
        recyclerView = findViewById(R.id.camera_list);
        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new CamsRVA(context, cameraList);

        recyclerView.setAdapter(recyclerViewAdapter);
        android.support.v7.widget.Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Live Camera's");
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        jsonParse();
    }

    private void jsonParse() {
        String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray JA = response.getJSONArray("Features");
                            for (int i = 0; i < JA.length(); i++){
                                JSONObject features = JA.getJSONObject(i);
                                JSONArray JAC = features.getJSONArray("Cameras");
                                for (int j = 0; j < JAC.length(); j++){
                                    JSONObject cameras = JAC.getJSONObject(j);
                                    String img;
                                    if(cameras.getString("Type").equals("sdot")){
                                        img = "http://www.seattle.gov/trafficcams/images/" + cameras.getString("ImageUrl");
                                    }else{
                                        img = "http://images.wsdot.wa.gov/nw/" + cameras.getString("ImageUrl");
                                    }
                                    cameraList.add(new CamsDetail(cameras.getString("Id"), cameras.getString("Description"), cameras.getString("Type"), img));
                                }
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }
            case R.id.action_setting: {
                Toast.makeText(Cams.this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_about: {
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_movies: {
                Intent intent = new Intent(this, Cams.class);
                startActivity(intent);
                return true;
            }
        }
        return true;
    }
}