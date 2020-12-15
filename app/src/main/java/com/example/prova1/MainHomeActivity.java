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
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {
    Button popularButton;
    Button upcomingButton;
    Button topRatedButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        Intent intent = getIntent();
        Utente utente = intent.getParcelableExtra("utente");
        System.out.println(utente.getNome()+utente.getUsername()+" "+utente.getUrl_foto());



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

        popularButton =(Button) findViewById(R.id.buttonPopular);
        popularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                intent.putExtra("categoria", "Popular");
                startActivity(intent);

            }
        });

        upcomingButton =(Button) findViewById(R.id.buttonUpcoming);
        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                intent.putExtra("categoria", "Upcoming");
                startActivity(intent);

            }
        });

        topRatedButton =(Button) findViewById(R.id.buttonTopRated);
        topRatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                intent.putExtra("categoria", "TopRated");
                startActivity(intent);

            }
        });






    }
}