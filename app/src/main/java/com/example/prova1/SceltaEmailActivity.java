package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SceltaEmailActivity extends AppCompatActivity {

    private ImageView foto;
    String image_url;
    private Button buttonConferma;
    private EditText textEmail;
    private Button next;
    String verifica;
    Dialog myDialog;
    ApiInterface apiInterface;
    Controller ctrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_email);
        View someView = findViewById(R.id.buttonConferma);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.lightGray));
        Intent intent = getIntent();
        ctrl = new Controller();
        final Utente utente = intent.getParcelableExtra("utente");

        myDialog = new Dialog(this);

        foto =(ImageView) findViewById(R.id.imgView);
        image_url = "";


        textEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonConferma=(Button) findViewById(R.id.buttonConferma);
        buttonConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = "";
                final String email = textEmail.getText().toString();
                if(email.matches("")){
                    Toast toast = Toast.makeText(SceltaEmailActivity.this, "Campo Email obbligatorio", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    toast.show();
                }else{
                    if(isEmailValid(email)){
                        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                        Call<Note> call = apiInterface.searchEmail(email);
                        call.enqueue(new Callback<Note>() {
                            @Override
                            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    verifica = response.body().getMessage();
                                      if(verifica.matches("false")){
                                           if(image_url.matches("")){
                                               Toast toast = Toast.makeText(SceltaEmailActivity.this, "Nessuna immagine selezionata", Toast.LENGTH_LONG);
                                               toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                               toast.show();

                                           }else{
                                               DaoUtente dao_utente = new DaoUtente();
                                               DaoPlaylist dao_playlist = new DaoPlaylist();
                                               utente.setUrl_foto(image_url);
                                               utente.setEmail(email);
                                               dao_utente.inserisciUtente(utente);

                                               Intent intent = new Intent(SceltaEmailActivity.this, MainHomeActivity.class);
                                               intent.putExtra("utente", utente);
                                               startActivity(intent);
                                           }

                                      }else{
                                          Toast toast = Toast.makeText(SceltaEmailActivity.this, "Email già in uso", Toast.LENGTH_LONG);
                                          toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                                          toast.show();
                                      }
                                } else {
                                    Toast.makeText(SceltaEmailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {

                                Toast.makeText(SceltaEmailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else{
                        Toast toast = Toast.makeText(SceltaEmailActivity.this, "Email non valida", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                        toast.show();

                    }
                }



            }
        });




    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void ShowPopup(View v){
        ImageView immagine_1;
        ImageView immagine_2;
        ImageView immagine_3;
        ImageView immagine_4;
        ImageView immagine_5;
        ImageView immagine_6;
        ImageView immagine_7;
        ImageView immagine_8;
        ImageView immagine_9;
        TextView textClose;
        myDialog.setContentView(R.layout.custompopup);
        textClose =(TextView) myDialog.findViewById(R.id.textClose);
        immagine_1 = (ImageView) myDialog.findViewById(R.id.imageView1);
        immagine_2 = (ImageView) myDialog.findViewById(R.id.imageView2);
        immagine_3 = (ImageView) myDialog.findViewById(R.id.imageView3);
        immagine_4 = (ImageView) myDialog.findViewById(R.id.imageView4);
        immagine_5 = (ImageView) myDialog.findViewById(R.id.imageView5);
        immagine_6 = (ImageView) myDialog.findViewById(R.id.imageView6);
        immagine_7 = (ImageView) myDialog.findViewById(R.id.imageView7);
        immagine_8 = (ImageView) myDialog.findViewById(R.id.imageView8);
        immagine_9 = (ImageView) myDialog.findViewById(R.id.imageView9);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        immagine_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_1.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_1));
                myDialog.dismiss();

            }
        });
        immagine_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_2.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_2));
                myDialog.dismiss();
            }
        });
        immagine_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_3.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_3));
                myDialog.dismiss();
            }
        });
        immagine_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_4.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_4));
                myDialog.dismiss();
            }
        });
        immagine_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_5.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_5));
                myDialog.dismiss();
            }
        });
        immagine_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_6.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_6));
                myDialog.dismiss();
            }
        });
        immagine_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_7.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_7));
                myDialog.dismiss();
            }
        });
        immagine_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_8.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_8));
                myDialog.dismiss();
            }
        });
        immagine_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_url="http://3.137.116.242/ic_profilo_9.png";
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_9));
                myDialog.dismiss();
            }
        });




        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();




    }








}


