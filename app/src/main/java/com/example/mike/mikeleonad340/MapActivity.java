package com.example.mike.mikeleonad340;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private List<CamsDetail> cameraList;
    Boolean mLocationPermissionGranted = false;

    GoogleMap mMap;

    FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        cameraList = new ArrayList<>();
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
                                JSONArray JPC = features.getJSONArray("PointCoordinate");
                                JSONArray JAC = features.getJSONArray("Cameras");
                                for (int j = 0; j < JAC.length(); j++){
                                    JSONObject cameras = JAC.getJSONObject(j);
                                    String img;
                                    if(cameras.getString("Type").equals("sdot")){
                                        img = "http://www.seattle.gov/trafficcams/images/" + cameras.getString("ImageUrl");
                                    }else{
                                        img = "http://images.wsdot.wa.gov/nw/" + cameras.getString("ImageUrl");
                                    }
                                    String description = cameras.getString("Description");
                                    Log.d("Description", description);
                                    String[] cordss = (JPC.toString().replace("},{,[,],,", " ").split(" "));
                                    cameraList.add(new CamsDetail(cameras.getString("Id"), description, cameras.getString("Type"), img, cordss));

                                }
                            }
                            getLocationPermission();
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
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
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Map ready");
        mMap = googleMap;
        Log.d("Json", Integer.toString(cameraList.size()));
        if (mLocationPermissionGranted) {
            Toast.makeText(this, "Map if statement", Toast.LENGTH_SHORT).show();
            getDeviceLocation();
            for(CamsDetail test: cameraList){
                        String[] wakka = test.getCords()[0].split("[^0-9.-]", 0);
                        Log.d("Cords", wakka[1] +"-"+ wakka[2]);
                        double lat = Double.parseDouble(wakka[1]);
                        double lng = Double.parseDouble(wakka[2]);
                        String url = test.getImageURL();
                        Marker m = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lng))
                        .title(test.getDescription())
                        .snippet(wakka[1] + " " + wakka[2]));
                        m.setTag(url);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }else{
            Toast.makeText(this, "ERROR YO", Toast.LENGTH_SHORT).show();
        }
    }
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Toast.makeText(this, "getDeviceLocation", Toast.LENGTH_SHORT).show();
        try {
            Toast.makeText(this, "try/catch", Toast.LENGTH_SHORT).show();
            if (mLocationPermissionGranted) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
                        } else {
                            Toast.makeText(MapActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lst: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        mMap.setInfoWindowAdapter(new MapDriver(MapActivity.this, cameraList));
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }





    private void getLocationPermission(){
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
            Toast.makeText(this, "Permission getter", Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(this, "FAILED2", Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(this, "FAILED3", Toast.LENGTH_SHORT).show();

                    mLocationPermissionGranted = true;
                    //Initialize Map
                    initMap();
                }
            }
        }
    }
}
