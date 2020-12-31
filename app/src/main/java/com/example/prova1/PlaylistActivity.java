package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    Button preferitiButton;
    Button daVedereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));

        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.playlist);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.playlist:
                        return true;
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(),MainHomeActivity.class);
                        intent.putExtra("utente",utente);
                        startActivity(intent);
                        overridePendingTransition(0,0);
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
        preferitiButton= (Button) findViewById(R.id.buttonPreferiti);
        preferitiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaylistActivity.this, PlaylistSceltaActivity.class);
                intent.putExtra("nome_playlist", "Preferiti");
                intent.putExtra("utente", utente);
                startActivity(intent);

            }
        });

        daVedereButton= (Button) findViewById(R.id.buttonDaVedere);
        daVedereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaylistActivity.this, PlaylistSceltaActivity.class);
                intent.putExtra("nome_playlist", "Da Vedere");
                intent.putExtra("utente", utente);
                startActivity(intent);

            }
        });






    }
}