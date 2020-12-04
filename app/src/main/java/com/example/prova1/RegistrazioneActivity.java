package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrazioneActivity extends AppCompatActivity {

    private Button button_registrati;
    private EditText textNome;
    private EditText textCognome;
    private EditText textUsername;
    private EditText textPassword;
    private EditText textConfermaPassword;
    private TextView textVerifica;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        View someView = findViewById(R.id.barra_superiore2);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        //response_username= new String();
        button_registrati = (Button) findViewById(R.id.button_confermaRegistrazione);
        textNome = (EditText) findViewById(R.id.editTextNome);
        textCognome = (EditText) findViewById(R.id.editTextCognome);
        textUsername = (EditText) findViewById(R.id.editTextUsername);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        textConfermaPassword = (EditText) findViewById(R.id.editTextConfermaPassword);


        button_registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textVerifica = (TextView) findViewById(R.id.textViewVerifica);
                String username = textUsername.getText().toString().trim();
                if (textNome.getText().toString().matches("") || textCognome.getText().toString().matches("") || textUsername.getText().toString().matches("") || textPassword.getText().toString().matches("") || textConfermaPassword.getText().toString().matches("")) {
                    Toast toast = Toast.makeText(RegistrazioneActivity.this, "Attenzione non tutti i campi sono stati compilati", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    toast.show();
                } else {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<Note> call = apiInterface.searchUsername(username);
                    call.enqueue(new Callback<Note>() {
                        @Override
                        public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                textVerifica.setText(response.body().getMessage());
                                //Toast.makeText(RegistrazioneActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                if (textVerifica.getText().toString().matches("true")) {
                                    Toast toast = Toast.makeText(RegistrazioneActivity.this, "Username già in uso", Toast.LENGTH_LONG);
                                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                    toast.show();

                                } else {
                                    if (!textPassword.getText().toString().matches(textConfermaPassword.getText().toString())) {
                                        Toast toast = Toast.makeText(RegistrazioneActivity.this, "Attenzione le due password non corrispondono", Toast.LENGTH_LONG);
                                        toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                        toast.show();
                                    } else {
                                        if (textPassword.getText().length() < 7) {
                                            Toast toast = Toast.makeText(RegistrazioneActivity.this, "Attenzione la password deve contenere almeno 8 caratteri", Toast.LENGTH_LONG);
                                            toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                            toast.show();
                                        } else {
                                            //TUTTO OK
                                            Utente utente = new Utente();
                                            utente.setNome(textNome.getText().toString());
                                            utente.setCognome(textCognome.getText().toString());
                                            utente.setUsername(textUsername.getText().toString());
                                            utente.setPassword(textPassword.getText().toString());
                                            Intent intent = new Intent(RegistrazioneActivity.this, SceltaEmailActivity.class);
                                            intent.putExtra("utente", utente);
                                            startActivity(intent);
                                            

                                        }
                                    }
                                }

                            } else {
                                Toast.makeText(RegistrazioneActivity.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {

                            Toast.makeText(RegistrazioneActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });




    }
}







