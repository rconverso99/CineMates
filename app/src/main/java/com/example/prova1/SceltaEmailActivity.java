package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SceltaEmailActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    private Bitmap yourSelectedImage;
    private ImageView foto;
    private Button buttonConferma;
    private EditText textEmail;
    String verifica;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_email);
        View someView = findViewById(R.id.barra_superiore3);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        Intent intent = getIntent();
        Utente utente = intent.getParcelableExtra("utente");

        foto =(ImageView) findViewById(R.id.imgView);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        textEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonConferma=(Button) findViewById(R.id.buttonConferma);
        buttonConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = new  String();
                String email = textEmail.getText().toString();
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

                                          //Inserisci nel DB con un metodo nella Classe Dao Inserisci Utente

                                          Intent intent = new Intent(SceltaEmailActivity.this, MainHomeActivity.class);
                                          startActivity(intent);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();

                    try {
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                    foto = (ImageView) findViewById(R.id.imgView);
                    foto.setImageBitmap(yourSelectedImage);
                }
        }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }






}


