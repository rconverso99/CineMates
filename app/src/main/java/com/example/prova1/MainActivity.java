package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    private Button myButton;
    private EditText textUsername;
    private EditText textPassword;
    private com.facebook.login.widget.LoginButton myFBbutton;
    private static int SPLASH_TIME_OUT = 3000;
    ApiInterface apiInterface;

    CallbackManager callbackManager = CallbackManager.Factory.create();

    private AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.barra_superiore);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        TextView textRegistrati = (TextView) findViewById(R.id.textRegistrati);
        textRegistrati.setPaintFlags(textRegistrati.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        textRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrazioneActivity.class);
                startActivity(intent);

            }
        });
        textUsername = (EditText) findViewById(R.id.editTextUsername);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        final AccessToken  accessToken= AccessToken.getCurrentAccessToken();
        boolean isLoggedIn =  accessToken!= null && !accessToken.isExpired();





        myFBbutton = (com.facebook.login.widget.LoginButton) findViewById(R.id.login_button);
        myFBbutton.setReadPermissions("email");








        myFBbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });

        if (isLoggedIn){

        }

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getApplicationContext(),"Fb success", LENGTH_SHORT).show();
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        myButton = (Button) findViewById(R.id.login);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textUsername.getText().toString().matches("") || textPassword.getText().toString().matches("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "Campi vuoti", LENGTH_SHORT);
                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    toast.show();
                }else {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<List<Note>> call = apiInterface.login(textUsername.getText().toString(),textPassword.getText().toString());
                    call.enqueue(new Callback<List<Note>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Note>> call, @NonNull Response<List<Note>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                             Utente user = new Utente();
                             user.setNome(response.body().get(0).getNome());
                             user.setCognome(response.body().get(0).getCognome());
                             user.setUsername(response.body().get(0).getUsername());
                             user.setEmail(response.body().get(0).getEmail());
                             user.setUrl_foto(response.body().get(0).getFoto());
                                System.out.println(user.getNome()+user.getUsername()+" "+user.getUrl_foto());
                                Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);
                                intent.putExtra("utente", user);
                                startActivity(intent);
                            }else{
                                Toast toast = Toast.makeText(MainActivity.this, "Utente non trovato", LENGTH_SHORT);
                                toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Note>> call,@NonNull Throwable t) {
                            Toast toast = Toast.makeText(MainActivity.this, "Utente non trovato", LENGTH_SHORT);
                            toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            toast.show();
                        }
                    });








                }






            }});




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

















    }






