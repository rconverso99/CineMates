package com.example.prova1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private Button next;
    String verifica;
    Dialog myDialog;
    ApiInterface apiInterface;
    Controller ctrl;
    int num_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_email);
        View someView = findViewById(R.id.buttonConferma);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));
        Intent intent = getIntent();
        ctrl = new Controller();
        Utente utente = intent.getParcelableExtra("utente");

        myDialog = new Dialog(this);

        foto =(ImageView) findViewById(R.id.imgView);

        num_click = 0;




       /* foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });*/
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


    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_1));
                myDialog.dismiss();

            }
        });
        immagine_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_2));
                myDialog.dismiss();
            }
        });
        immagine_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_3));
                myDialog.dismiss();
            }
        });
        immagine_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_4));
                myDialog.dismiss();
            }
        });
        immagine_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_5));
                myDialog.dismiss();
            }
        });
        immagine_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_6));
                myDialog.dismiss();
            }
        });
        immagine_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_7));
                myDialog.dismiss();
            }
        });
        immagine_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = (ImageView) findViewById(R.id.imgView);
                foto.setImageBitmap(BitmapFactory.decodeResource(SceltaEmailActivity.this.getResources(),
                        R.drawable.ic_profilo_8));
                myDialog.dismiss();
            }
        });
        immagine_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


