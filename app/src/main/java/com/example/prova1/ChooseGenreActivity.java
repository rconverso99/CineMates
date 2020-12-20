package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseGenreActivity extends AppCompatActivity {
    Button action_button;
    Button adventure_button;
    Button animation_button;
    Button comedy_button;
    Button crime_button;
    Button documentary_button;
    Button drama_button;
    Button family_button;
    Button fantasy_button;
    Button history_button;
    Button horror_button;
    Button romance_button;
    Button thriller_button;
    Button war_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_genre);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
                        Intent intent1 = new Intent(getApplicationContext(),MainHomeActivity.class);
                        intent1.putExtra("utente",utente);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        Intent intent2 = new Intent(getApplicationContext(),ProfiloActivity.class);
                        intent2.putExtra("utente",utente);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        action_button = (Button) findViewById(R.id.buttonAction);
        action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","28");
                intent.putExtra("nome_genere","Azione");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        adventure_button = (Button) findViewById(R.id.buttonAdventure);
        adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","12");
                intent.putExtra("nome_genere","Avventura");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        animation_button = (Button) findViewById(R.id.buttonAnimation);
        animation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","16");
                intent.putExtra("nome_genere","Animazione");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        comedy_button = (Button) findViewById(R.id.buttonComedy);
        comedy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","35");
                intent.putExtra("nome_genere","Commedia");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        crime_button = (Button) findViewById(R.id.buttonCrime);
        crime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","80");
                intent.putExtra("nome_genere","Crime");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        documentary_button = (Button) findViewById(R.id.buttonDocumentary);
        documentary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","99");
                intent.putExtra("nome_genere","Documentari");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        drama_button = (Button) findViewById(R.id.buttonDrama);
        drama_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","18");
                intent.putExtra("nome_genere","Drammatici");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        family_button = (Button) findViewById(R.id.buttonFamily);
        family_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","10751");
                intent.putExtra("nome_genere","Famiglia");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        fantasy_button = (Button) findViewById(R.id.buttonFantasy);
        fantasy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","14");
                intent.putExtra("nome_genere","Fantasy");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        history_button = (Button) findViewById(R.id.buttonHistory);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","36");
                intent.putExtra("nome_genere","Storia");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        horror_button = (Button) findViewById(R.id.buttonHorror);
        horror_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","27");
                intent.putExtra("nome_genere","Horror");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        romance_button = (Button) findViewById(R.id.buttonRomance);
        romance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","10749");
                intent.putExtra("nome_genere","Romantici");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        thriller_button = (Button) findViewById(R.id.buttonThriller);
        thriller_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","53");
                intent.putExtra("nome_genere","Thriller");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });

        war_button = (Button) findViewById(R.id.buttonWar);
        war_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GenreActivity.class);
                intent.putExtra("genre","10752");
                intent.putExtra("nome_genere","Guerra");
                intent.putExtra("utente",utente);
                startActivity(intent);

            }
        });





    }
}