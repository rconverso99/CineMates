package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    private Button myButton;
    private Button bottoneRegistrati;
    private TextView myText;
    private Button myConnect;
    private com.facebook.login.widget.LoginButton myFBbutton;
    private ListView listView;
    String email_loggata;
    CallbackManager callbackManager = CallbackManager.Factory.create();

    private static final String BASE_URL ="http://3.137.116.242/query.php";
    private AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.barra_superiore);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        myText = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);
        myConnect = (Button) findViewById(R.id.connetti);
        myConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON("http://3.137.116.242/query.php");

            }
        });
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
            System.out.print("GUARDAMIIII"+Profile.getCurrentProfile().getName());
            openRicercaFilm();
        }


        myButton = (Button) findViewById(R.id.login);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRicercaFilm();


            }
        });
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

        bottoneRegistrati=(Button) findViewById(R.id.buttonRegistrati);
        bottoneRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrazioneActivity.class);
                startActivity(intent);


            }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }













    public void openRicercaFilm(){
        Intent intent = new Intent(this, RicercaFilm.class);
        intent.putExtra(EXTRA_TEXT,Profile.getCurrentProfile().getName());
        startActivity(intent);

    }

    //this method is actually fetching the json string
    private void getJSON(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try{
                    loadIntoListView(s);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        Integer[] heroes = new Integer[jsonArray.length()];

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
            heroes[i] = obj.getInt("numeri");
        }

        //the array adapter to load data into list
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, heroes);

        //attaching adapter to listview

        listView.setAdapter(arrayAdapter);
    }



}