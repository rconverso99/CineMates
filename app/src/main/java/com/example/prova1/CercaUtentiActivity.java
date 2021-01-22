package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;
import com.example.prova1.adapter.UserAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CercaUtentiActivity extends AppCompatActivity {
SearchView searchView;
ApiInterface apiInterface;
RecyclerView recyclerUser;
    private LinearLayoutManager linearLayoutManager;
    private UserAdapter adapter;
    List<Note> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_utenti);
        View someView = findViewById(R.id.bottom_navigation);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent = getIntent();
        final Utente utente = intent.getParcelableExtra("utente");
        recyclerUser = findViewById(R.id.recyclerUser);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerUser.setHasFixedSize(true);
        recyclerUser.setLayoutManager(linearLayoutManager);
        userList= new ArrayList<>();



        searchView = (SearchView) findViewById(R.id.searchViewUtenti);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.lightPrimary));
        textView.setHintTextColor(getResources().getColor(R.color.lightPrimary));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText == null || newText.matches("") || newText.matches(" ") || newText.contains("  ")) {

                } else {
                    final ArrayList<Note> listOfUsers = new ArrayList<Note>();
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<List<Note>> call = apiInterface.cercaUtente(newText);
                    call.enqueue(new Callback<List<Note>>() {
                        @Override
                        public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                               /* for(Note i : response.body()){
                                    listOfUsers.add(i);
                                }
                                userList.addAll(listOfUsers);
                                if(userList.isEmpty()){
                                    Toast toast = Toast.makeText(CercaUtentiActivity.this, "Nessun risultato trovato", Toast.LENGTH_SHORT);
                                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                    toast.show();
                                }
                                adapter = new UserAdapter(CercaUtentiActivity.this, userList);
                                recyclerUser.setAdapter(adapter);
                                recyclerUser.addOnItemTouchListener(new RecyclerItemClickListener(CercaUtentiActivity.this, recyclerUser, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(getApplicationContext(),UtenteSelezionatoActivity.class);
                                        intent.putExtra("utente_selezionato", (Parcelable) userList.get(position));
                                        intent.putExtra("utente",utente);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }
                                }));*/
                                System.out.println("OOOOOOOOOOO"+response.body().get(0).getNome());



                            }



                        }

                        @Override
                        public void onFailure(Call<List<Note>> call, Throwable t) {
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