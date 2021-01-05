package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {
    Button popularButton;
    Button upcomingButton;
    Button topRatedButton;
    Button searchButton;
    Button genreButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        System.out.println(utente.getNome()+utente.getUsername()+" "+utente.getUrl_foto());



        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.playlist:
                        Intent intent = new Intent(getApplicationContext(),PlaylistActivity.class);
                        intent.putExtra("utente",utente);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        Intent intent1 = new Intent(getApplicationContext(),ProfiloActivity.class);
                        intent1.putExtra("utente",utente);
                        startActivity(intent1);
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
                intent.putExtra("utente", utente);
                startActivity(intent);

            }
        });

        upcomingButton =(Button) findViewById(R.id.buttonUpcoming);
        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                intent.putExtra("categoria", "Upcoming");
                intent.putExtra("utente", utente);
                startActivity(intent);

            }
        });

        topRatedButton =(Button) findViewById(R.id.buttonTopRated);
        topRatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, CategoryActivity.class);
                intent.putExtra("categoria", "TopRated");
                intent.putExtra("utente", utente);
                startActivity(intent);

            }
        });

        genreButton =(Button) findViewById(R.id.buttonGenre);
        genreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, ChooseGenreActivity.class);
                intent.putExtra("utente", utente);
                startActivity(intent);
            }
        });

      searchButton = (Button) findViewById(R.id.buttonSearch);
      searchButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainHomeActivity.this, SearchActivity.class);
              intent.putExtra("utente", utente);
              startActivity(intent);

          }
      });





    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent setIntent = new Intent(MainHomeActivity.this,MainActivity.class);
            startActivity(setIntent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Clicca due volte per uscire", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}