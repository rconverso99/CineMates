package com.example.prova1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoUtente {
    ApiInterface apiInterface;

    public void inserisciUtente(Utente user){
        System.out.println(user.getUrl_foto());
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.registrazione(user.getNome(),user.getCognome(),user.getUsername(),user.getPassword(),user.getEmail(),user.getUrl_foto());
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
