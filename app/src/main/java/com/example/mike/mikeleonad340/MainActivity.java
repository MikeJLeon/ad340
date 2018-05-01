package com.example.mike.mikeleonad340;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mike.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_setting){
            Toast.makeText(MainActivity.this,
                        "Settings", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.action_about){
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.action_movies){
            Intent intent = new Intent(this, list.class);
            startActivity(intent);
        }
        return true;
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, submit.class);
        EditText input = findViewById(R.id.input);
        String message = input.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
