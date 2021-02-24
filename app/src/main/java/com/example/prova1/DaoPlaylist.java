package com.example.prova1;

import android.content.ClipData;

import com.example.prova1.ApiClient;
import com.example.prova1.ApiInterface;
import com.example.prova1.MovieResults;
import com.example.prova1.Note;
import com.example.prova1.Utente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoPlaylist {
    ApiInterface apiInterface;
    public void insertPlaylist(String username, String tipo , Integer cod_movie){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.inserisci_film("",username,"",tipo,cod_movie);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {

            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });




    }

    public void rimuoviPlaylist(String username, String tipo, Integer cod_movie){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.elimina_film(cod_movie,username,tipo);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {

            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });




    }






}
