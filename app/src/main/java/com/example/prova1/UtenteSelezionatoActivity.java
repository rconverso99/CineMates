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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtenteSelezionatoActivity extends AppCompatActivity {
    TextView usernameText;
    ImageView fotoProfilo;
    Button segui_button,seguiti_button,follower_button;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente_selezionato);

        View someView = findViewById(R.id.buttonSeguitiSelected);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        final Utente utente_selezionato = intent.getParcelableExtra("utente_selezionato");
        segui_button = (Button) findViewById(R.id.buttonSegui);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.elencaSeguiti(utente.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                for(Note i: response.body()){
                    if(i.getSegue_username().matches(utente_selezionato.getUsername())){
                        segui_button.setText("Segui già");
                        segui_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        segui_button.setBackgroundResource(R.drawable.button_genre_family);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        seguiti_button= findViewById(R.id.buttonSeguitiSelected);
        follower_button=findViewById(R.id.buttonFollowerSelected);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call3 = apiInterface.contaSeguiti(utente_selezionato.getUsername());
        call3.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call3, Response<List<Note>> response) {
                seguiti_button.setText(response.body().get(0).getCount()+"\nSeguiti");
            }

            @Override
            public void onFailure(Call<List<Note>> call3, Throwable t) {

            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call2 = apiInterface.contaFollower(utente_selezionato.getUsername());
        call2.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call2, Response<List<Note>> response) {
                follower_button.setText(response.body().get(0).getCount()+"\nFollowers");
            }

            @Override
            public void onFailure(Call<List<Note>> call2, Throwable t) {
                t.printStackTrace();
            }
        });

        segui_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(segui_button.getText().toString().matches("Segui")) {
                    segui_button.setText("Segui già");
                    segui_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    segui_button.setBackgroundResource(R.drawable.button_genre_family);
                    DaoUtente dao_utente = new DaoUtente();
                    dao_utente.segue(utente.getUsername(), utente_selezionato.getUsername());
                }else{
                    //ELIMINA SEGUITO
                    segui_button.setText("Segui");
                    segui_button.setTextColor(getResources().getColor(R.color.white0));
                    segui_button.setBackgroundResource(R.drawable.follow_button);
                }



            }
        });


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