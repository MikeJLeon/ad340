package com.example.mike.mikeleonad340;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;


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
}
