package com.example.mike.mikeleonad340;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String EXTRA_MESSAGE = "com.example.mike.MESSAGE";
    SharedPreferences inputPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolBar, R.string.openDescription, R.string.closeDescription);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(isServicesOk()){
            Context context = getApplicationContext();
            String text = "Services Okay";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Grab stored input if exists
        inputPrefs = getSharedPreferences("storeInput", Context.MODE_PRIVATE);
        String savedInput = inputPrefs.getString("input", "");
        EditText output = findViewById(R.id.input);
        output.setText(savedInput, TextView.BufferType.EDITABLE);
    }
    public boolean isServicesOk() {
        Log.d("Main Activity", "isServicesOk: checking version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d("Main Activity", "Google services is working");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d("Main Activity", "Google services had error but its fixable");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, 9001);
            dialog.show();
        }else{
            Toast.makeText(this, "Cant make map requests", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }
    public boolean phoneOnline(){
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean online = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            online = true;
        }
        return online;
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.action_about:
                intent = new Intent(this, about.class);
                startActivity(intent);
                break;
            case R.id.action_movies:
                intent = new Intent(this, list.class);
                startActivity(intent);
                break;
            case R.id.action_liveCams:
                intent = new Intent(this, Cams.class);
                startActivity(intent);
                break;
            case R.id.action_setting:
                Toast.makeText(MainActivity.this,
                        "Settings", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(MainActivity.this,
                    "Settings", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, submit.class);
        EditText input = findViewById(R.id.input);
        String message = input.getText().toString();
        if(!validateInput(message)){
            //Input is empty, error
            Toast.makeText(MainActivity.this, "Input is empty", Toast.LENGTH_SHORT).show();
        }else{
            //Store input and open next activity
            SharedPreferences.Editor editor = inputPrefs.edit();
            editor.putString("input", message);
            editor.apply();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }
    public boolean validateInput(String test) {
        return !test.isEmpty() && test.trim().length() != 0;
    }
    public void movieList(View view) {
        Intent intent = new Intent(this, list.class);
        startActivity(intent);
    }
    public void liveCams(View view){
        if(phoneOnline()) {
            Intent intent = new Intent(this, Cams.class);
            startActivity(intent);
        }else{
            Context context = getApplicationContext();
            String text = "Network offline";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    public void MapActivity(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
    public void toast3(View view){
        Context context = getApplicationContext();
        String text = "Android is fun";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
