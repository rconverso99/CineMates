package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.playlist:
                        startActivity(new Intent(getApplicationContext(),PlaylistActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfiloActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
        final ImageView popularImage = (ImageView) findViewById(R.id.popular);
        popularImage.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ){

                        popularImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.popular_button_pressed));
                     return true;
                    }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    popularImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.popular_button));
                    Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                    intent.putExtra("categoria", "Popular");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        final ImageView latestImage = (ImageView) findViewById(R.id.latest);
        latestImage.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ){

                    latestImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.latest_button_pressed));
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    latestImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.latest_button));
                    return true;
                }
                return false;
            }
        });
        final ImageView topRatedImage = (ImageView) findViewById(R.id.topRated);
        topRatedImage.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ){

                    topRatedImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.top_rated_button_pressed));
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    topRatedImage.setImageBitmap(BitmapFactory.decodeResource(MainHomeActivity.this.getResources(),R.drawable.top_rated_button));
                    return true;
                }
                return false;
            }
        });





    }
}