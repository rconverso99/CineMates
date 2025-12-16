package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmInComuneActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviewAdapter adapter;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_in_comune);

        View someView = findViewById(R.id.textFilmInComune);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));

        recyclerView = (RecyclerView)findViewById(R.id.recyclerFilmInComune);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        final Utente utente_selezionato = intent.getParcelableExtra("utente_selezionato");
        final ArrayList<Integer> film_utente= new ArrayList<Integer>();

        TextView text = (TextView) findViewById(R.id.textFilmInComune);
        text.setText("Preferiti in comune con "+utente_selezionato.getUsername());

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.recupera_playlist(utente.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                for(Note i:response.body()){
                    if(i.getTipo_playlist().matches("pref")){
                        film_utente.add(i.getCod_film());



                    }
                }
                confrontaFilm(film_utente,utente,utente_selezionato);

            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast toast = Toast.makeText(FilmInComuneActivity.this, "Tu e "+utente_selezionato.getUsername()+" non avete film preferiti in comune", Toast.LENGTH_LONG);
                toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                toast.show();
            }
        });


    }

    public void confrontaFilm(final ArrayList<Integer> film_utente, final Utente utente_loggato, final Utente utente_selezionato){
        final ArrayList<Integer> film_in_comune = new ArrayList<Integer>();
        film_in_comune.clear();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.recupera_playlist(utente_selezionato.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                for(Note i:response.body()){
                    if(i.getTipo_playlist().matches("pref")){
                        if(film_utente.contains(i.getCod_film())){
                            film_in_comune.add(i.getCod_film());

                        }
                    }
                }
                if(film_in_comune.isEmpty()){
                    Toast toast = Toast.makeText(FilmInComuneActivity.this, "Tu e "+utente_selezionato.getUsername()+" non avete film preferiti in comune", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    toast.show();
                }else {
                    makeFilmInComuneList(film_in_comune, utente_loggato);
                }


            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });



    }

    public void makeFilmInComuneList(final ArrayList<Integer> film_in_comune, final Utente utente_loggato){
        final Controller ctrl = new Controller();
        final ArrayList <MovieResults> movies= new ArrayList<>();
        for(Integer cod_film : film_in_comune){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface myInterface = retrofit.create(ApiInterface.class);
            Call<MovieResults> call = myInterface.movieById(cod_film, "2e3034b8c110830b946972c7d30f5cb5");
            call.enqueue(new Callback<MovieResults>() {
                @Override
                public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                    if(response.isSuccessful() && response.body() !=null){
                        MovieResults results = response.body();
                        MovieResults movie = new MovieResults();
                        movie.setTitle(results.getTitle());
                        movie.setId(results.getId());
                        movie.setBackdropPath(results.getBackdropPath());
                        movie.setPosterPath(results.getPosterPath());
                        movie.setOverview(results.getOverview());
                        movie.setReleaseDate(results.getReleaseDate());
                        movies.add(movie);
                        if(movies.size()==film_in_comune.size()){
                            final MovieResults movieResults = new MovieResults();
                            final ArrayList<MovieResults.Result> res = movieResults.creaListaResults(movies);
                            adapter = new MoviewAdapter(res, FilmInComuneActivity.this);
                            recyclerView.setAdapter(adapter);


                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(FilmInComuneActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    myDialog = new Dialog(FilmInComuneActivity.this);
                                    ctrl.ShowPopup(res.get(position),myDialog,utente_loggato,FilmInComuneActivity.this);
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {
                                }}));





                        }



                    }
                }

                @Override
                public void onFailure(Call<MovieResults> call, Throwable t) {

                }
            });



        }





    }

}