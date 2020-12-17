package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
SearchView searchView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviewAdapter adapter;
    Dialog myDialog;
    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "2e3034b8c110830b946972c7d30f5cb5";
    public static String LANGUAGE = "ita";
    List<MovieResults.Result> movieslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));

        myDialog = new Dialog(this);

        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");


        searchView = (SearchView) findViewById(R.id.searchView);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.rgb(255, 143, 0));
        recyclerView = (RecyclerView)findViewById(R.id.recyclerMovies);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        movieslist= new ArrayList<>();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText == null || newText.matches("") || newText.matches(" ") || newText.contains("  ")) {

                } else {
                    if(!movieslist.isEmpty()){
                        movieslist.clear();}
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface myInterface = retrofit.create(ApiInterface.class);
                    Call<MovieResults> call = myInterface.searchMovies(API_KEY, newText, LANGUAGE, PAGE);
                    call.enqueue(new Callback<MovieResults>() {
                        @Override
                        public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                            MovieResults results = response.body();
                            final List<MovieResults.Result> listOfMovies = results.getResults();
                            movieslist.addAll(listOfMovies);
                            adapter = new MoviewAdapter(movieslist, SearchActivity.this);
                            recyclerView.setAdapter(adapter);

                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SearchActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    ShowPopup(movieslist.get(position));
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
                return false;
            }

        });











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