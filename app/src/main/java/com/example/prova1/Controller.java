package com.example.prova1;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Controller {

    public boolean verificaRegistrazione(String nome, String cognome, String username, String password, String conferma_password, Activity context) {
        {
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

    public  Bitmap getBitmapFromStringUrl(String image_string){
        try {
            URL url = new URL(image_string);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return image;
        } catch(IOException e) {
            System.out.println(e);
        }
       return null;


    }
    public void ShowPopup(MovieResults.Result movie, final Dialog myDialog) {
        myDialog.setContentView(R.layout.popupmovie);
        TextView textClose;
        textClose =(TextView) myDialog.findViewById(R.id.textClose2);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        ImageView posterMovie;
        posterMovie = (ImageView) myDialog.findViewById(R.id.imageMovie);
        TextView titleText;
        titleText = (TextView) myDialog.findViewById(R.id.textTitleMovie);
        TextView descripionText;
        descripionText = (TextView) myDialog.findViewById(R.id.textDescriptionMovie);
        TextView dateText;
        dateText = (TextView) myDialog.findViewById(R.id.textDateMovie);
        RetrieveImageTask task = new RetrieveImageTask();
        try {
            posterMovie.setImageBitmap(task.execute(movie.getPosterPath()).get());
            titleText.setText(movie.getTitle());
            descripionText.setText(movie.getOverview());
            dateText.setText(movie.getReleaseDate());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    public class RetrieveImageTask extends AsyncTask<String , Void, Bitmap> {

        private Exception exception;
        @Override
        protected Bitmap doInBackground(String... image_string) {

            try{
                URL url = new URL(image_string[0]);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return image;

            }catch(Exception e){
                this.exception = e;
                return null;
            }

        }
        protected void onPostExecute(Bitmap image) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }



}


