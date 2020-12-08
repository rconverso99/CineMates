package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RicercaFilm extends AppCompatActivity {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "2e3034b8c110830b946972c7d30f5cb5";
    public static String LANGUAGE = "ita";
    public static String CATEGORY = "popular";
    public String email="none";
    private TextView emailText;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }


    private TextView myTextView;
    private ImageView myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_film);
       Intent intent = getIntent();
       email=intent.getStringExtra(MainActivity.EXTRA_TEXT);
        myTextView=(TextView)findViewById(R.id.textView2);
        myImage=(ImageView)findViewById(R.id.imageView);
        emailText= (TextView)findViewById(R.id.emailText);
        emailText.setText(email);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call = myInterface.listOfMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieResults>() {


            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                List<MovieResults.Result> listOfMovies = results.getResults();
                MovieResults.Result firstMovie = listOfMovies.get(0);

                System.out.println(firstMovie.getTitle());
                myTextView.setText(firstMovie.getTitle());
                String urlLink = firstMovie.getPosterPath();
                if(urlLink.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Immagine inesistente"
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    LoadImage loadImage = new LoadImage(myImage);
                    System.out.println(firstMovie.getPosterPath());
                    loadImage.execute(urlLink);


                }



            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });




    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView myImage){
            this.imageView = myImage;

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream= new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            myImage.setImageBitmap(bitmap);
        }
    }
}