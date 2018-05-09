package com.example.mike.mikeleonad340;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Grab stored input if exists
        inputPrefs = getSharedPreferences("storeInput", Context.MODE_PRIVATE);
        String savedInput = inputPrefs.getString("input", "");
        EditText output = findViewById(R.id.input);
        output.setText(savedInput, TextView.BufferType.EDITABLE);
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
        if(test.isEmpty() || test.trim().length() == 0) {
            return false;
        }else {
            return true;
        }
    }
    public void movieList(View view) {
        Intent intent = new Intent(this, list.class);
        startActivity(intent);
    }
    public void toast1(View view){
        Context context = getApplicationContext();
        String text = "Sup";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void toast2(View view){
        Context context = getApplicationContext();
        String text = "Pizza is great";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void toast3(View view){
        Context context = getApplicationContext();
        String text = "Android is fun";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
