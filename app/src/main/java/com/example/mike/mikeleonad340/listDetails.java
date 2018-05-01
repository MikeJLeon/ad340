package com.example.mike.mikeleonad340;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
}
