package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenreActivity extends AppCompatActivity {
    public String genere="none";
    public String nome_genere ="none";
    private TextView textcategoria;
    Dialog myDialog;
    ApiInterface apiInterface;
    public static String BASE_URL = "https://api.themoviedb.org";
    public static String API_KEY = "2e3034b8c110830b946972c7d30f5cb5";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        View someView = findViewById(R.id.recycler_genre);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        myDialog = new Dialog(this);
        final Controller ctrl = new Controller();
        textcategoria = (TextView) findViewById(R.id.textCategoria);
        Intent intent = getIntent();
        genere = intent.getStringExtra("genre");
        nome_genere = intent.getStringExtra("nome_genere");
        final Utente utente = intent.getParcelableExtra("utente");

        textcategoria.setText(nome_genere);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_genre);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface myInterface = retrofit.create(ApiInterface.class);
        Call<MovieResults> call = myInterface.genreMovies(API_KEY,genere);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                final List<MovieResults.Result> listOfMovies = results.getResults();
                adapter = new MoviewAdapter(listOfMovies, GenreActivity.this);
                recyclerView.setAdapter(adapter);


                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(GenreActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ctrl.ShowPopup(listOfMovies.get(position),myDialog,utente,null);
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
}