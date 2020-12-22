package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));

        myDialog = new Dialog(this);
        final Controller ctrl = new Controller();
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");


        searchView = (SearchView) findViewById(R.id.searchView);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));
        recyclerView = (RecyclerView)findViewById(R.id.recyclerMovies);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        movieslist= new ArrayList<>();
        searchView.requestFocusFromTouch();

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
                                    ctrl.ShowPopup(movieslist.get(position),myDialog);

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


}