package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    public String categoria="none";
    private TextView textcategoria;
    Dialog myDialog;
    ApiInterface apiInterface;
    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "2e3034b8c110830b946972c7d30f5cb5";
    public static String LANGUAGE = "ita";
    public static String CATEGORY = "popular";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        View someView = findViewById(R.id.textCategoria);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        myDialog = new Dialog(this);

        textcategoria = (TextView) findViewById(R.id.textCategoria);
        Intent intent = getIntent();
        categoria = intent.getStringExtra("categoria");
        textcategoria.setText(categoria);

        //gestione film
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface myInterface = retrofit.create(ApiInterface.class);
        Call<MovieResults> call = myInterface.listOfMovies(tipoCategoria(categoria),API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                final List<MovieResults.Result> listOfMovies = results.getResults();
               adapter = new MoviewAdapter(listOfMovies, CategoryActivity.this);
               recyclerView.setAdapter(adapter);


                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CategoryActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ShowPopup(listOfMovies.get(position));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }}));


            }
            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });





    }

    public String tipoCategoria(String cat){
        if (cat.matches("Popular")){
            return "popular";
        }else if(cat.matches("Upcoming")){
            return "upcoming";
        }else if(cat.matches("TopRated")){
            return "top_rated";
        }
        return null;
    }
    public void ShowPopup(MovieResults.Result movie) {
        myDialog.setContentView(R.layout.popupmovie);
        TextView textClose;
        textClose =(TextView) myDialog.findViewById(R.id.textClose2);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        ImageView posterMovie;
        posterMovie = (ImageView) myDialog.findViewById(R.id.imageMovie);
        TextView titleText;
        titleText = (TextView) myDialog.findViewById(R.id.textTitleMovie);
        TextView descripionText;
        descripionText = (TextView) myDialog.findViewById(R.id.textDescriptionMovie);
        TextView dateText;
        dateText = (TextView) myDialog.findViewById(R.id.textDateMovie);
        RetrieveImageTask task = new RetrieveImageTask();
        try {
            posterMovie.setImageBitmap(task.execute(movie.getPosterPath()).get());
            titleText.setText(movie.getTitle());
            descripionText.setText(movie.getOverview());
            dateText.setText(movie.getReleaseDate());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }
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

