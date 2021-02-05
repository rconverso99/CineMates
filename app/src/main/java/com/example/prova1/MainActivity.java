package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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

        myFBbutton = (com.facebook.login.widget.LoginButton) findViewById(R.id.login_button);


        CallbackManager callbackManager = CallbackManager.Factory.create();

        //myFBbutton.setPermissions(Arrays.asList(""));
         myFBbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
             @Override
             public void onSuccess(LoginResult loginResult) {
               Log.d("Project","Login Successful!");









             }

             @Override
             public void onCancel() {
                 Log.d("Project","Login Canceled");
             }

             @Override
             public void onError(FacebookException error) {
                 Log.d("Project","Login Error");
             }
         });

        LoginManager.getInstance().logOut();
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

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
             Log.d("Project", object.toString());
                try {



                    String nome_completo = object.getString("name");
                    String nome = object.getString("first_name");
                    String cognome = object.getString("last_name");
                    String id = object.getString("id");
                    String email = object.getString("email");
                    String foto = "https://graph.facebook.com/"+object.getString("id")+"/picture?type=large";
                    int index = email.indexOf('@');
                    String username = email.substring(0,index)+id.substring(0,3);
                    Controller ctrl = new Controller();
                    if(ctrl.esisteUsername(username)){

                        Utente user = new Utente();
                        user.setNome(nome);
                        user.setCognome(cognome);
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setUrl_foto(foto);
                        Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);
                        intent.putExtra("utente", user);
                        startActivity(intent);

                    }else {

                        Utente user = new Utente();
                        user.setNome(nome);
                        user.setCognome(cognome);
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setUrl_foto(foto);

                        DaoUtente dao_utente = new DaoUtente();
                        dao_utente.inserisciUtente(user);

                        Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);
                        intent.putExtra("utente", user);
                        startActivity(intent);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
           Bundle bundle = new Bundle();
           bundle.putString("fields","name,id,first_name,last_name,email,picture");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();


    }
    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                LoginManager.getInstance().logOut();
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }


}






