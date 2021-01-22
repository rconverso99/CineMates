package com.example.prova1.adapter;

import android.content.Context;
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
import com.example.prova1.R;

import java.util.List;

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
        Glide.with(context).load(listOfUsers.get(position).getFoto()).into(holder.imageProfilo);
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
}
