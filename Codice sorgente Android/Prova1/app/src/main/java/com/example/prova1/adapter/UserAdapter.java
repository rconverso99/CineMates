package com.example.prova1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prova1.MovieResults;
import com.example.prova1.Note;
import com.example.prova1.ProfiloActivity;
import com.example.prova1.R;
import com.example.prova1.SceltaEmailActivity;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

        private List <Note> listOfUsers;
    private Context context;

    public UserAdapter (Context ct, List<Note> userList ){
            context = ct;
            listOfUsers = userList;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // Glide.with(context).load(listOfUsers.get(position).getFoto()).into(holder.imageProfilo);
        RetrieveImageTask task = new RetrieveImageTask();
        try {
            holder.imageProfilo.setImageBitmap(task.execute(listOfUsers.get(position).getFoto()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.textUsername.setText(listOfUsers.get(position).getUsername());


    }

    @Override
    public int getItemCount() {
        return listOfUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textUsername;
        ImageView imageProfilo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textUsername = itemView.findViewById(R.id.textUsernameUtente);
            imageProfilo = itemView.findViewById(R.id.imageProfiloUtente);

        }
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
