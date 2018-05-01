package com.example.mike.mikeleonad340;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class listDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetails);
        Intent intent = getIntent();
        String imgURL = intent.getStringExtra("detail_img");
        ImageView zImg = findViewById(R.id.zImg);
        Glide.with(this).asBitmap().load(imgURL).into(zImg);
        TextView zTitle = findViewById(R.id.zTitle);
        String zText = intent.getStringExtra("detail_title");
        zTitle.setText(zText);

        TextView zYear = findViewById(R.id.zYear);
        zYear.setText(intent.getStringExtra("detail_year"));
        TextView zDirector = findViewById(R.id.zDirector);
        zDirector.setText(intent.getStringExtra("detail_director"));
        TextView zContent = findViewById(R.id.zContent);
        zContent.setText(intent.getStringExtra("detail_summary"));

        android.support.v7.widget.Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        if(zText != null) {
            getSupportActionBar().setTitle(zText);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
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
                Toast.makeText(listDetails.this, "Settings", Toast.LENGTH_SHORT).show();
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
