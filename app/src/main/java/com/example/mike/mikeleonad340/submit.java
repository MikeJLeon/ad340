package com.example.mike.mikeleonad340;

import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


public class submit extends AppCompatActivity {
    private static final String TAG = submit.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Intent intent = getIntent();
        String input = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView output = findViewById(R.id.output);
        output.setText(input);
        Log.d(TAG, "Output activity created");

        android.support.v7.widget.Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Display Message");
        mToolBar.setTitleTextColor(Color.WHITE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Output activity started");
    }
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Output activity resumed");
    }
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Output activity paused");
    }
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Output activity stopped");
    }
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Output activity restarted");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Output activity destroyed");
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
                Toast.makeText(submit.this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_about: {
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_movies: {
                Intent intent = new Intent(this, list.class);
                startActivity(intent);
                return true;
            }
        }
        return true;
    }
}
