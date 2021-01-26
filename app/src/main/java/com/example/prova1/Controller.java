package com.example.prova1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova1.adapter.MoviewAdapter;
import com.example.prova1.adapter.RecyclerItemClickListener;
import com.example.prova1.adapter.UserAdapter;
import com.example.prova1.ui.DaoPlaylist;

import org.w3c.dom.Node;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    ApiInterface apiInterface;

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
    public void ShowPopup(final MovieResults.Result movie, final Dialog myDialog, final Utente user, final Activity activity) {
        myDialog.setContentView(R.layout.popupmovie);
        final Button addPreferiti;
        final Button addDaVedere;
        addPreferiti =(Button) myDialog.findViewById(R.id.addPreferiti);
        addDaVedere = (Button) myDialog.findViewById(R.id.addDaVedere);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.recupera_playlist(user.getUsername());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for(Note i : response.body()){
                        System.out.println("PROVAAA"+i.getCod_film());
                        System.out.println("PROVAAA"+i.getTipo_playlist());
                        System.out.println("PROVAAA"+user.getUsername());
                        if(i.getTipo_playlist().matches("pref")){
                            if(i.getCod_film()!= 0 && i.getCod_film().equals(movie.getId())){
                                setButtonRimuoviPlaylist(addPreferiti,myDialog,R.drawable.ic_preferito,"RIMUOVI DAI PREFERITI");
                            }
                        }

                    }
                    for(Note i : response.body()){
                        if(i.getTipo_playlist().matches("davedere")){
                            if(i.getCod_film()!= null && i.getCod_film().intValue()==movie.getId()){
                                setButtonRimuoviPlaylist(addDaVedere,myDialog,R.drawable.ic_davedere,"RIMUOVI DAI DA VEDERE");
                            }
                        }

                    }


                }
            }
            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
        TextView textClose;
        textClose =(TextView) myDialog.findViewById(R.id.textClose2);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity!=null){
               activity.recreate();}

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

       addPreferiti.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(addPreferiti.getText().toString().matches("RIMUOVI DAI PREFERITI")){
                    setButtonAggiungiPlaylist(addPreferiti,myDialog,R.drawable.ic_preferito,"AGGIUNGI AI PREFERITI");
                    DaoPlaylist daoPlaylist = new DaoPlaylist();
                    daoPlaylist.rimuoviPlaylist(user.getUsername(),"pref",movie.getId());



               }else{
                   setButtonRimuoviPlaylist(addPreferiti,myDialog,R.drawable.ic_preferito,"RIMUOVI DAI PREFERITI");
                   DaoPlaylist daoPlaylist = new DaoPlaylist();
                   daoPlaylist.insertPlaylist(user.getUsername(),"pref",movie.getId());

               }



           }
       });

        addDaVedere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addDaVedere.getText().toString().matches("RIMUOVI DAI DA VEDERE")){
                    setButtonAggiungiPlaylist(addDaVedere,myDialog,R.drawable.ic_davedere,"AGGIUNGI A DA VEDERE");
                    DaoPlaylist daoPlaylist = new DaoPlaylist();
                    daoPlaylist.rimuoviPlaylist(user.getUsername(),"davedere",movie.getId());



                }else{
                    setButtonRimuoviPlaylist(addDaVedere,myDialog,R.drawable.ic_davedere,"RIMUOVI DAI DA VEDERE");
                    DaoPlaylist daoPlaylist = new DaoPlaylist();
                    daoPlaylist.insertPlaylist(user.getUsername(),"davedere",movie.getId());

                }


            }
        });

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

    public void setButtonRimuoviPlaylist(Button btn,final Dialog myDialog, int drawable_icon, String text){
        btn.setText(text);
       btn.setBackgroundResource(R.drawable.button_genre_horror);
        btn.setTextColor(Color.RED);
        int tintColor = ContextCompat.getColor(myDialog.getContext(), android.R.color.holo_red_dark);

        Drawable drawable = ContextCompat.getDrawable(myDialog.getContext(), drawable_icon);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable.mutate(), tintColor);
        drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        btn.setCompoundDrawables(drawable, null, null, null);
    }

    public void setButtonAggiungiPlaylist(Button btn, final Dialog myDialog, int drawable_icon, String text){
        btn.setText(text);
        btn.setBackgroundResource(R.drawable.button_genre_family);
        btn.setTextColor(myDialog.getContext().getResources().getColor(R.color.colorPrimaryDark));
        int tintColor = ContextCompat.getColor(myDialog.getContext(), R.color.colorPrimaryDark);
        Drawable drawable = ContextCompat.getDrawable(myDialog.getContext(), drawable_icon);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable.mutate(), tintColor);
        drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        btn.setCompoundDrawables(drawable, null, null, null);
    }


    public void showPopupSearch(final Activity activity, final Dialog myDialog, final Utente user){

        final String BASE_URL = "https://api.themoviedb.org";
         final int PAGE = 1;
        final String API_KEY = "2e3034b8c110830b946972c7d30f5cb5";
         final String LANGUAGE = "ita";
        myDialog.setContentView(R.layout.popupsearch);
        TextView textClose;
        textClose =(TextView) myDialog.findViewById(R.id.textClose3);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.recreate();
                myDialog.dismiss();
            }
        });
        SearchView searchView;
        searchView= (SearchView) myDialog.findViewById(R.id.searchView2);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(activity.getResources().getColor(R.color.lightPrimary));
        textView.setHintTextColor(activity.getResources().getColor(R.color.lightPrimary));
        final RecyclerView recyclerView;
        recyclerView = (RecyclerView)myDialog.findViewById(R.id.recyclerMoviesToAdd);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager= new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        final List<MovieResults.Result> movieslist= new ArrayList<>();
        searchView.setFocusable(true);
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText == null || newText.matches("") || newText.matches(" ") || newText.contains("  ")) {

                } else {
                    if(!movieslist.isEmpty()){
                        movieslist.clear();}
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface myInterface = retrofit.create(ApiInterface.class);
                    Call<MovieResults> call = myInterface.searchMovies(API_KEY, newText, LANGUAGE, PAGE);
                    call.enqueue(new Callback<MovieResults>() {
                        @Override
                        public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                            MovieResults results = response.body();
                            final List<MovieResults.Result> listOfMovies = results.getResults();
                            movieslist.addAll(listOfMovies);
                            if(movieslist.isEmpty()){
                                Toast toast = Toast.makeText(activity.getApplicationContext(), "Nessun risultato trovato", Toast.LENGTH_SHORT);
                                toast.getView().setBackgroundColor(activity.getResources().getColor(android.R.color.holo_red_dark));
                                toast.show();
                            }
                            final MoviewAdapter adapter;
                            adapter = new MoviewAdapter(movieslist, activity.getApplicationContext());
                            recyclerView.setAdapter(adapter);

                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(activity.getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Dialog dial= new Dialog(activity);
                                    ShowPopup(movieslist.get(position),dial ,user,null);
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {
                                }}));
                        }

                        @Override
                        public void onFailure(Call<MovieResults> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
                return false;
            }

        });








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void showUserPopup(final Activity activity, final Dialog myDialog, final Utente user, String tipo_categoria, final Utente utente_loggato){
        myDialog.setContentView(R.layout.popupuser);
        TextView textClose;
        textClose =(TextView) myDialog.findViewById(R.id.textClose4);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        final ArrayList<String> lista_username = new ArrayList<>();

        TextView textTipo = myDialog.findViewById(R.id.textTipo);
        textTipo.setText(tipo_categoria);

        if(tipo_categoria.matches("Seguiti")){
            lista_username.clear();
            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<List<Note>> call = apiInterface.seguiti(user.getUsername());
            call.enqueue(new Callback<List<Note>>() {
                @Override
                public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                    for(Note i: response.body()){
                      lista_username.add(i.getSegue_username());



                    }
                    makeUserList(lista_username,activity,myDialog,user,utente_loggato);









                }

                @Override
                public void onFailure(Call<List<Note>> call, Throwable t) {

                }
            });





        }else{
            lista_username.clear();
            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<List<Note>> call = apiInterface.follower(user.getUsername());
            call.enqueue(new Callback<List<Note>>() {
                @Override
                public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                    for(Note i: response.body()){
                        lista_username.add(i.getUsername());



                    }
                    makeUserList(lista_username,activity,myDialog,user,utente_loggato);









                }

                @Override
                public void onFailure(Call<List<Note>> call, Throwable t) {

                }
            });




        }

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    public void makeUserList(final ArrayList<String> listaUsername, final Activity activity, Dialog myDialog, final Utente user,final Utente utente_loggato){

        final RecyclerView recyclerView;
        recyclerView = (RecyclerView)myDialog.findViewById(R.id.recyclerUserList);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager= new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progress = new ProgressDialog(myDialog.getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.setProgressStyle(R.style.ProgressBar);
        progress.show();

        final ArrayList <Note> lista_utenti= new ArrayList<>();
        for ( String username : listaUsername){
            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<List<Note>> call = apiInterface.cercaUtente(username);
            call.enqueue(new Callback<List<Note>>() {
                @Override
                public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                    lista_utenti.add(response.body().get(0));
                    if(lista_utenti.size()==listaUsername.size()){
                        progress.dismiss();
                        UserAdapter adapter = new UserAdapter(activity.getApplicationContext(),lista_utenti);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(activity.getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(activity.getApplicationContext(),UtenteSelezionatoActivity.class);
                                Utente user_selezionato = new Utente();
                                user_selezionato.setNome(lista_utenti.get(position).getNome());
                                user_selezionato.setCognome(lista_utenti.get(position).getCognome());
                                user_selezionato.setUsername(lista_utenti.get(position).getUsername());
                                user_selezionato.setUrl_foto(lista_utenti.get(position).getFoto());
                                user_selezionato.setPassword(lista_utenti.get(position).getPassword());
                                user_selezionato.setEmail(lista_utenti.get(position).getEmail());
                                if(!user_selezionato.getUsername().matches(utente_loggato.getUsername())){
                                    intent.putExtra("utente_selezionato", user_selezionato);
                                    intent.putExtra("utente",utente_loggato);
                                    activity.startActivity(intent);
                                }else{
                                    Toast toast = Toast.makeText(activity.getApplicationContext(), "Questo utente ti segue o è seguito da te", Toast.LENGTH_SHORT);
                                    toast.show();
                                }



                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        }));

                    }

                }

                @Override
                public void onFailure(Call<List<Note>> call, Throwable t) {

                }
            });
        }







    }



}


