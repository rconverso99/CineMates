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
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfiloActivity extends AppCompatActivity {
    TextView usernameText;
    ImageView fotoProfilo;
    Button seguiti_button;
    Button follower_button;
    ApiInterface apiInterface;
    Utente user = new Utente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent= getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        user = utente;
        usernameText = (TextView) findViewById(R.id.textUsername);
        fotoProfilo = (ImageView) findViewById(R.id.imgProfilo);
        usernameText.setText(utente.getUsername());
        RetrieveImageTask task = new RetrieveImageTask();
        try {
            fotoProfilo.setImageBitmap(task.execute(utente.getUrl_foto()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        seguiti_button= findViewById(R.id.buttonSeguiti);
        follower_button=findViewById(R.id.buttonFollower);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.contaSeguiti(utente.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                seguiti_button.setText(response.body().get(0).getCount()+"\nSeguiti");
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call2 = apiInterface.contaFollower(utente.getUsername());
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







        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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


    @Override
    public void onBackPressed() {

            Intent setIntent = new Intent(ProfiloActivity.this, MainHomeActivity.class);
            setIntent.putExtra("utente", user);
            startActivity(setIntent);


        }




}