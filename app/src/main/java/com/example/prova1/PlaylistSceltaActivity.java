package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class PlaylistSceltaActivity extends AppCompatActivity {
    public String nome_playlist="none";
    private TextView textplaylist;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviewAdapter adapter;
    private Dialog myDialog;
    private Dialog myDialogSearch;
    private Button addButton;
    ApiInterface apiInterface;
    static ArrayList<MovieResults> MoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_scelta);

        View someView = findViewById(R.id.textPlaylist);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));


        textplaylist = (TextView) findViewById(R.id.textPlaylist);
        Intent intent = getIntent();
        nome_playlist = intent.getStringExtra("nome_playlist");
        final Utente utente = intent.getParcelableExtra("utente");
        textplaylist.setText(nome_playlist);
        MoviesList=new ArrayList<>();


        recyclerView = (RecyclerView)findViewById(R.id.recyclerPlaylist);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        final Controller ctrl = new Controller();
        final MovieResults movieResults = new MovieResults();

        final ArrayList<Integer> lista_codici_film = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.recupera_playlist(utente.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    int count = 0;
                    for(Note i : response.body()){
                        if(i.getTipo_playlist().matches(tipoPlaylist(nome_playlist))){
                            count = count+1;
                            if(i.getCod_film().intValue()!=0){
                                //QUI
                                lista_codici_film.add(i.getCod_film().intValue());

                            }

                        }
                    }
                    makeMovieList(lista_codici_film,utente);
                    if(count==1){
                        Toast toast = Toast.makeText(PlaylistSceltaActivity.this, "Questa playlist è vuota", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                        toast.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
      addButton= findViewById(R.id.addMovie);
      addButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              myDialogSearch = new Dialog(PlaylistSceltaActivity.this);
             ctrl.showPopupSearch(PlaylistSceltaActivity.this,myDialogSearch,utente);
          }
      });





    }

    public String tipoPlaylist(String cat){
        if (cat.matches("Preferiti")){
            return "pref";
        }else if(cat.matches("Da Vedere")){
            return "davedere";
        }else{
            return "personale";
        }
    }


    public void makeMovieList(final ArrayList<Integer> codici_film, final Utente utente) {
        final ArrayList <MovieResults> movies= new ArrayList<>();
        final Controller ctrl = new Controller();
        for ( Integer cod_film : codici_film) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface myInterface = retrofit.create(ApiInterface.class);

            Call<MovieResults> call = myInterface.movieById(cod_film, "2e3034b8c110830b946972c7d30f5cb5");
            call.enqueue(new Callback<MovieResults>() {
                @Override
                public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        MovieResults results = response.body();
                        MovieResults movie = new MovieResults();
                        movie.setTitle(results.getTitle());
                        movie.setId(results.getId());
                        movie.setBackdropPath(results.getBackdropPath());
                        movie.setPosterPath(results.getPosterPath());
                        movie.setOverview(results.getOverview());
                        movie.setReleaseDate(results.getReleaseDate());
                        movies.add(movie);
                        if(movies.size()==codici_film.size()){
                            final MovieResults movieResults = new MovieResults();
                            final ArrayList<MovieResults.Result> res = movieResults.creaListaResults(movies);
                            System.out.println("OOOOOOOOOOO"+res.get(0).getBackdropPath());
                            adapter = new MoviewAdapter(res, PlaylistSceltaActivity.this);
                            recyclerView.setAdapter(adapter);


                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(PlaylistSceltaActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    myDialog = new Dialog(PlaylistSceltaActivity.this);
                                    ctrl.ShowPopup(res.get(position),myDialog,utente,PlaylistSceltaActivity.this);
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {
                                }}));





                        }



                    }



                }

                @Override
                public void onFailure(Call<MovieResults> call, Throwable t) {
                    t.printStackTrace();

                }
            });

        }


    }

}