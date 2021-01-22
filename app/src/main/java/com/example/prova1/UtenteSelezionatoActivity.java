package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;
import java.util.concurrent.ExecutionException;

public class UtenteSelezionatoActivity extends AppCompatActivity {
    TextView usernameText;
    ImageView fotoProfilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente_selezionato);

        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        final Utente utente_selezionato = intent.getParcelableExtra("utente_selezionato");


        usernameText = (TextView) findViewById(R.id.textUsernameSelected);
        fotoProfilo = (ImageView) findViewById(R.id.imgProfiloSelected);
        usernameText.setText(utente_selezionato.getUsername());
       RetrieveImageTask task = new RetrieveImageTask();
        try {
            fotoProfilo.setImageBitmap(task.execute(utente_selezionato.getUrl_foto()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_utente_selezionato);

        bottomNavigationView.setSelectedItemId(R.id.profile);

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


    }

    //DA UTILIZZARE PER RECUPERARE LE IMMAGINI
    public class RetrieveImageTask extends AsyncTask<String , Void, Bitmap> {

        private Exception exception;
        @Override
        protected Bitmap doInBackground(String... image_string) {

            try{
                URL url = new URL(image_string[0]);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return image;

            }catch(Exception e){
                this.exception = e;
                return null;
            }

        }
        protected void onPostExecute(Bitmap image) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }

}