package com.example.prova1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrazioneActivity extends AppCompatActivity {

    private Button button_registrati;
    private EditText textNome;
    private EditText textCognome;
    private EditText textUsername;
    private EditText textPassword;
    private EditText textConfermaPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        View someView = findViewById(R.id.barra_superiore2);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        button_registrati =(Button) findViewById(R.id.button_confermaRegistrazione);
        textNome=(EditText) findViewById(R.id.editTextNome);
        textCognome=(EditText) findViewById(R.id.editTextCognome);
        textUsername=(EditText) findViewById(R.id.editTextUsername);
        textPassword=(EditText) findViewById(R.id.editTextPassword);
        textConfermaPassword=(EditText) findViewById(R.id.editTextConfermaPassword);

        button_registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textNome.getText().toString().matches("") || textCognome.getText().toString().matches("") || textUsername.getText().toString().matches("") || textPassword.getText().toString().matches("") || textConfermaPassword.getText().toString().matches("")){
                    Toast toast =  Toast.makeText(RegistrazioneActivity.this, "Attenzione non tutti i campi sono stati compilati", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    toast.show();
                }else{
                    if(!textPassword.getText().toString().matches(textConfermaPassword.getText().toString())){
                        Toast toast =  Toast.makeText(RegistrazioneActivity.this, "Attenzione le due password non corrispondono", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                        toast.show();
                    }else{
                        if(textPassword.getText().length()<7){
                            Toast toast =  Toast.makeText(RegistrazioneActivity.this, "Attenzione la password deve contenere almeno 8 caratteri", Toast.LENGTH_LONG);
                            toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            toast.show();
                        }else{
                            //Registrazione andata a buon fine
                        }






                    }




                }






            }
        });


    }
}