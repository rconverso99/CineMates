package com.example.prova1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    public boolean verificaRegistrazione(String nome, String cognome,String username,  String password ,  String conferma_password,  Activity context){
        {
            //String username = textUsername.getText().toString().trim();
            if (nome.matches("") || cognome.matches("") || username.matches("") || password.matches("") || conferma_password.matches("")) {
                Toast toast = Toast.makeText(context, "Attenzione non tutti i campi sono stati compilati", Toast.LENGTH_LONG);
                toast.getView().setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
                toast.show();
            } else {

                                if (!password.matches(conferma_password)) {
                                    Toast toast = Toast.makeText(context, "Attenzione le due password non corrispondono", Toast.LENGTH_LONG);
                                    toast.getView().setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
                                    toast.show();
                                } else {
                                    if (password.length() < 8) {
                                        Toast toast = Toast.makeText(context, "Attenzione la password deve contenere almeno 8 caratteri", Toast.LENGTH_LONG);
                                        toast.getView().setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
                                        toast.show();
                                    } else {
                                        return true;



                                    }
                                }
                            }



            }

            return false;




    }





    public FileOutputStream getFileFromImage(Bitmap image, Activity context) throws IOException {
        File f = new File(context.getCacheDir(), "filename");
        f.createNewFile();

//Convert bitmap to byte array
        Bitmap bitmap = image;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return fos;



    }




}
