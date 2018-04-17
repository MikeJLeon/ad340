package com.example.mike.mikeleonad340;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class submit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Intent intent = getIntent();
        String input = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView output = findViewById(R.id.output);
        output.setText(input);
    }
}
