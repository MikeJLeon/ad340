package com.example.mike.mikeleonad340;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        android.support.v7.widget.Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("About");
        mToolBar.setTitleTextColor(Color.WHITE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
                Toast.makeText(about.this, "Settings", Toast.LENGTH_SHORT).show();
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
